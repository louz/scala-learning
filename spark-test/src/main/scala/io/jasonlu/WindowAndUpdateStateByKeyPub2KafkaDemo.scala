package io.jasonlu

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf}

/**
 * 场景：时间窗口内累计发生金额，有变化时做更新，并将更新发布至Kafka
 *
 * Created by Louz on 2016/5/18.
 */
object WindowAndUpdateStateByKeyPub2KafkaDemo {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

    // every 10 second produce a batch
    val ssc = new StreamingContext(conf, Seconds(10))

    // window operation must set checkpoint
    ssc.checkpoint("/tmp")

    val lines = ssc.socketTextStream("localhost", 9999)
    val trans = lines.map(_.split(" ")).map(tran => (tran(0), BigDecimal(tran(1))))

    val updateAmout2 = (iterator: Iterator[(String, Seq[BigDecimal], Option[BigDecimal])]) => {
      iterator.flatMap(t => update(t._1, t._2, t._3).map(s => (t._1, s)))
    }

    val newTrans = trans.window(Seconds(30), Seconds(10)).updateStateByKey(updateAmout2, new HashPartitioner(10), false)
    newTrans.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
  }

  /*def updateAmout(values:Seq[BigDecimal], oldState:Option[BigDecimal]) = {
    val sumValue = values.sum
    if (sumValue != oldState.getOrElse(0)) {
      Some(sumValue)
    } else {
      oldState
    }
  }*/


  def logUpdate(key: String, oldValue: BigDecimal, newValue: BigDecimal): Unit = {
    println("Key:" + key + " updated: " + oldValue + "->" + newValue)

    val topic = "my-topic"
    val record = new ProducerRecord[String, String](topic, key, newValue.toString())
    ProducerDemo.getKafkaProducer().send(record)
  }

  def update(key: String, values: Seq[BigDecimal], oldState: Option[BigDecimal]): Option[BigDecimal] = {

    val sumValue = values.sum
    if (sumValue != oldState.getOrElse(0)) {
      logUpdate(key, oldState.getOrElse(0), sumValue)

      if (sumValue == 0) {
        None
      } else {
        Some(sumValue)
      }
    } else {
      oldState
    }
  }


}
