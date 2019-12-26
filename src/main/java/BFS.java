package main.java;

import edu.uci.ics.jung.graph.Graph;

import java.util.ArrayList;

public class BFS {

    String way = "";// переменная для хранения пути от начальной точки до конечной
    ArrayList<Integer> allResultWays = new ArrayList<>();
    int firstElementQueue;// текущий элемент вершины
    int actualLevel = 1;// уровень на котором находится вершина
    int maxSizeQueue;// максимальный размер очереди
    int[] propertiesVertex = new int[2];// хранит номер точки и уровень
    ArrayList<int[]> propertiesAllVertex = new ArrayList<>();
    ArrayList<Integer> vertexUsed = new ArrayList<>();// хранит использованные вершины

    public String search(Graph g, int numberVertexStart, int numberVertexEnd) {

        // цикл до тех пор пока очередь не будет пуста
        int numberVertex;// название вершины
        maxSizeQueue = g.getVertices().size() * 2;//так как в очереди хранится и номер уровня
        Queue queue = new Queue(maxSizeQueue);// очередь для хранения вершин графа
        queue.insert(numberVertexStart);
        queue.insert(actualLevel);
        propertiesVertex[0] = numberVertexStart;
        propertiesVertex[1] = actualLevel;
        propertiesAllVertex.add(propertiesVertex);

        while (queue != null) {
            firstElementQueue = queue.remove();
            actualLevel = queue.remove();
            vertexUsed.add(firstElementQueue);
            Object[] mass = g.getNeighbors(firstElementQueue).toArray();
            if (firstElementQueue == numberVertexEnd) {
                allResultWays.add(firstElementQueue);
                recursion(g, firstElementQueue, actualLevel, numberVertexStart);
                queue = null;
                break;
            }
            for (Object object : mass) {
                if (!vertexUsed.contains((int) object)) {
                    queue.insert((Integer) object);// вставляем номер вершины
                    queue.insert(actualLevel + 1);// вставляем ее уровень
                    int[] propertiesVertex = new int[2];
                    propertiesVertex[0] = (Integer) object;
                    propertiesVertex[1] = actualLevel + 1;
                    propertiesAllVertex.add(propertiesVertex);
                    propertiesVertex = null;
                    // вот тут помечать
                }
            }
        }
        for (int i = allResultWays.size() - 1; i >= 0; i--) {
            way = way + " " + allResultWays.get(i);
        }

        return way;
    }

    public void recursion(Graph<Integer, String> g, int numberVertex, int levelEndVertex, int numberVertexStart) {

        Object[] mass = g.getNeighbors(numberVertex).toArray();

        for (Object object : mass) {

            for (int i = 0; i < (Integer) propertiesAllVertex.size(); i++) {
                propertiesVertex = null;
                int[] propertiesVertex = propertiesAllVertex.get(i);
                if (propertiesVertex[0] == (int) object) {
                    if (propertiesAllVertex.get(i)[1] == (levelEndVertex - 1)) {
                        if(levelEndVertex > 1){
                            numberVertex = propertiesAllVertex.get(i)[0];
                            levelEndVertex = levelEndVertex - 1;
                            allResultWays.add(numberVertex);
                            recursion(g, numberVertex, levelEndVertex, numberVertexStart);
                        }
                        else{
                            return;
                        }
                    }
                }
            }
        }
    }

}
