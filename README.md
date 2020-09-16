# Wiki-Vote-Network-Analysis-Spark-Graphx
Wikipedia voting on promotion to administratorship (till January 2008). Directed edge A->B means user A voted on B becoming Wikipedia administrator.


# Analysis using spark Graphx
* Input: ./file/Wiki-vote.txt
* Output: ./file/Vote-results.txt/part-00007

# Tasks:
1. Find Top 5 outdegrees and outgoing edges
2. Find the top 5 nodes with the highest indegree and find the count of the number of incoming edges
3. Calculate PageRank for each of the nodes and output the top 5 nodes with the highest PageRank
values
4. Run the connected components algorithm on it and find the top 5 components with the largest
number of nodes
5. Run the triangle counts algorithm on each of the vertices and output the top 5 vertices with the
largest triangle count
