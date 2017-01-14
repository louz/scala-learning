package io.jasonlu

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 场景：计算时间窗内累计次数
 *
 * Created by Louz on 2016/5/17.
 */
object WindowDemo {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

    // every 10 second produce a batch
    val ssc = new StreamingContext(conf, Seconds(10))

    // window operation must set checkpoint
    ssc.checkpoint("/tmp")

    val lines = ssc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(" "))

    val wordCount = words.countByValueAndWindow(Seconds(30), Seconds(20));
    wordCount.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate
  }
}
