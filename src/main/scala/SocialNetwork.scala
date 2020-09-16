import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx._

object SocialNetwork {
  def main(args: Array[String]): Unit = {
    // Input output paths:
    val inputpath = "C:\\Users\\zailinyuan\\IdeaProjects\\SocialNetwork\\file\\Wiki-Vote.txt"
    val outputpath = "C:\\Users\\zailinyuan\\IdeaProjects\\SocialNetwork\\file\\Vote-results.txt"

    // Environment:
    val sparkConf = new SparkConf().setAppName("Social Network Analysis").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    // Create graph from input data:
    val input_str = sc.textFile(inputpath)
    val wiki = input_str.filter(x=> !x.contains("#")).map(_.split("""\t"""))
    val vertices = wiki.flatMap(x => x).distinct().map(v => (v.toLong, v))
    val edges = wiki.map(e => Edge(e(0).toLong, e(1).toLong, 1))
    val graph = Graph(vertices, edges)

    // Ananlysis:
    var output_str = ""
    output_str = output_str.concat("1. Top 5 outdegrees and outgoing edges in each\nNode:\toutDegrees:\n")
    graph.outDegrees.sortBy(_._2, ascending=false).take(5)
      .foreach(x => {
        output_str += x._1 + "\t" + x._2 + "\n"
      })

    output_str =  output_str.concat("\n2. Top 5 indegree and incoming edges in each\nNode:\tinDegrees:\n")
    graph.inDegrees.sortBy(_._2, ascending=false).take(5)
      .foreach(x => {
        output_str += x._1 + "\t" + x._2 + "\n"
      })

    output_str = output_str.concat("\n3. Calculate PageRank for each of the nodes and output the top 5 nodes with the highest PageRank values. You are free to deﬁne the threshold parameter.\nNode:\tPageRank:\n")
    graph.pageRank(0.1).vertices.sortBy(_._2, ascending=false).take(5)
      .foreach(x => {
        output_str += x._1 + "\t" + x._2 + "\n"
      })

    output_str = output_str.concat("\n4. Run the connected components algorithm on it and ﬁnd the top 5 components with the largest number of nodes.\nNode:\tconnectedComponent:\n")
    graph.connectedComponents().vertices.sortBy(_._2, ascending=false).take(5)
      .foreach(x => {
        output_str += x._1 + "\t" + x._2 + "\n"
      })

    output_str = output_str.concat("\n5. Run the triangle counts algorithm on each of the vertices and output the top 5 vertices with the largest triangle count. In case of ties, you can randomly select the top 5 vertices.\nNode:\ttrangleCounts:\n")
    graph.triangleCount().vertices.sortBy(_._2, ascending=false).take(5)
      .foreach(x => {
        output_str += x._1 + "\t" + x._2 + "\n"
      })

    sc.parallelize(Seq(output_str)).saveAsTextFile(outputpath)
  }
}
