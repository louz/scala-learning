package io.jasonlu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 如：计算当天累计金额
 *
 * Created by Louz on 2016/5/18.
 */
object UpdateStateByKeyDemo {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

    // every 10 second produce a batch
    val ssc = new StreamingContext(conf, Seconds(10))

    // window operation must set checkpoint
    ssc.checkpoint("/tmp")

    val lines = ssc.socketTextStream("localhost", 9999)
    val trans = lines.map(_.split(" ")).map(tran => (tran(0), BigDecimal(tran(1))))

    val newTrans = trans.updateStateByKey(updateAmout _)
    newTrans.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
  }

  def updateAmout(values:Seq[BigDecimal], oldState:Option[BigDecimal]) = {
    if (values.size > 0) {
      Some(values.sum + oldState.getOrElse(0))
    } else {
      oldState
    }
  }
}
