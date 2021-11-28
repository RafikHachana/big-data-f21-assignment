package againhamzatheGOAT

import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.Rating
import scala.util.Random

import java.io.File
import java.io.PrintWriter


class AutomaticGrader(path: String, sc: SparkContext) {

  // constructor begins

  val graded = Random.shuffle(
    sc
      .textFile(path + "/user-ratings.tsv")
      .map{_.split(" ")}
      .map{x => (x(0).toInt, x(1).toDouble)}
      .collect()
      .toSeq
  )

  def toRDD = {
    sc.parallelize(this.graded.map{x => Rating(0, x._1, x._2)})
  }
}
