import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class Kota {
    private Map<Integer, String> cityMap;
    private Map<Integer, Set<Integer>> edges;
    private int[][] adjacencyMatrix;
    private int[] distance;

    public Kota() {
        cityMap = new HashMap<>();
        edges = new HashMap<>();
    }

    public void addCity(int index, String cityName) {
        cityMap.put(index, cityName);
        edges.put(index, new HashSet<>());
    }

    public void addEdge(int fromIndex, int toIndex, int weight) {
        edges.get(fromIndex).add(toIndex);
        edges.get(toIndex).add(fromIndex);
        adjacencyMatrix[fromIndex][toIndex] = weight;
        adjacencyMatrix[toIndex][fromIndex] = weight;
    }

    public void initializeAdjacencyMatrix(int size) {
        adjacencyMatrix = new int[size][size];
    }

    public void displayCities() {
        for (Map.Entry<Integer, String> entry : cityMap.entrySet()) {
            System.out.println("Index " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void displayEdges() {
        for (Map.Entry<Integer, Set<Integer>> entry : edges.entrySet()) {
            int fromIndex = entry.getKey();
            Set<Integer> connectedCities = entry.getValue();

            System.out.print("Edges from " + cityMap.get(fromIndex) + ": ");
            for (int toIndex : connectedCities) {
                int weight = adjacencyMatrix[fromIndex][toIndex];
                System.out.print(cityMap.get(toIndex) + " (Weight: " + weight + ") ");
            }
            System.out.println();
        }

    }

    public void displayAdjacencyMatrix() {
        System.out.print("          ");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.printf("%-12s", cityMap.get(i));
        }
        System.out.println();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.printf("%-12s", cityMap.get(i));
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.printf("%-12d", adjacencyMatrix[i][j]);
            }
            System.out.println();
        }
    }

    private int getIndexByCityName(String cityName) {
        for (Map.Entry<Integer, String> entry : cityMap.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(cityName)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public void shortPath(int start, int end) {
        int numVertices = cityMap.size();
        distance = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        int[] parent = new int[numVertices];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);

        distance[start] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] != 0 && distance[u] != Integer.MAX_VALUE
                        && distance[u] + adjacencyMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + adjacencyMatrix[u][v];
                    parent[v] = u;
                }
            }
        }

        printShortestPath(start, end, parent);
    }

    private int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < distance.length; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private void printShortestPath(int start, int end, int[] parent) {
        LinkedList<Integer> path = new LinkedList<>();
        int current = end;

        while (current != -1) {
            path.addFirst(current);
            current = parent[current];
        }

        System.out.print("Rute Terpendek dari Kota " + cityMap.get(start) + " ke Kota " + cityMap.get(end) + ": ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(cityMap.get(path.get(i)));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nTotal Jarak: " + distance[end] + " km");
    }

    static void menu() {
        System.out.println("1. Tampilkan Adjacency Matrix");
        System.out.println("2. Cari Rute Terpendek");
        System.out.println("3. Exit");
        System.out.print("Pilihan: ");
    }

    public static void main(String[] args) {
        // Membuat objek HashMap untuk menyimpan informasi kota
        Scanner input = new Scanner(System.in);
        int pilihan;
        Kota graphKota = new Kota();

        // Menambahkan informasi kota ke dalam HashMap
        graphKota.addCity(0, "Surabaya");
        graphKota.addCity(1, "Gresik");
        graphKota.addCity(2, "Sidoarjo");
        graphKota.addCity(3, "Mojokerto");
        graphKota.addCity(4, "Jombang");
        graphKota.addCity(5, "Bojonegoro");
        graphKota.addCity(6, "Lamongan");
        graphKota.addCity(7, "Tuban");
        graphKota.addCity(8, "Madiun");
        graphKota.addCity(9, "Ngawi");
        graphKota.addCity(10, "Magetan");
        graphKota.addCity(11, "Ponorogo");
        graphKota.addCity(12, "Pacitan");
        graphKota.addCity(13, "Kediri");
        graphKota.addCity(14, "Nganjuk");
        graphKota.addCity(15, "Tulungagung");
        graphKota.addCity(16, "Blitar");
        graphKota.addCity(17, "Trenggalek");
        graphKota.addCity(18, "Malang");
        graphKota.addCity(19, "Pasuruan");
        graphKota.addCity(20, "Probolinggo");
        graphKota.addCity(21, "Lumajang");
        graphKota.addCity(22, "Bondowoso");
        graphKota.addCity(23, "Situbondo");
        graphKota.addCity(24, "Jember");
        graphKota.addCity(25, "Banyuwangi");
        graphKota.addCity(26, "Bangkalan");
        graphKota.addCity(27, "Sampang");
        graphKota.addCity(28, "Pamekasan");
        graphKota.addCity(29, "Sumenep");

        graphKota.initializeAdjacencyMatrix(graphKota.cityMap.size());

        // Menambahkan edges
        graphKota.addEdge(0, 1, 18); // surabaya - gresik
        graphKota.addEdge(0, 26, 28); // surabaya - bangkalan
        graphKota.addEdge(0, 2, 23); // surabaya - sidoarjo
        graphKota.addEdge(0, 3, 49); // surabaya - mojokerto
        graphKota.addEdge(1, 6, 27); // gresik - lamongan
        graphKota.addEdge(1, 26, 46); // gresik - bangkalan
        graphKota.addEdge(2, 3, 72); // sidoarjo - mojokerto
        graphKota.addEdge(2, 18, 66); // sidoarjo - malang
        graphKota.addEdge(2, 19, 37); // sidoarjo - pasuruhan
        graphKota.addEdge(3, 4, 30); // mojokerto - jombang
        graphKota.addEdge(4, 13, 44); // jombang - kediri
        graphKota.addEdge(4, 14, 40); // jombang - nganjuk
        graphKota.addEdge(5, 7, 65); // bojonegoro - tuban
        graphKota.addEdge(5, 14, 125); // bojonegoro - nganjuk
        graphKota.addEdge(5, 9, 78); // bojonegoro - ngawi
        graphKota.addEdge(6, 7, 58); // lamongan - tuban
        graphKota.addEdge(8, 9, 32); // madiun - ngawi
        graphKota.addEdge(8, 10, 24); // madiun - magetan
        graphKota.addEdge(8, 11, 29); // madiun - ponorogo
        graphKota.addEdge(8, 14, 50); // madiun - nganjuk
        graphKota.addEdge(9, 10, 34); // ngawi - magetan
        graphKota.addEdge(10, 11, 53); // magetan - ponorogo
        graphKota.addEdge(11, 12, 78); // ponoroo - pacitan
        graphKota.addEdge(11, 17, 52); // ponorogo - trenggalek
        graphKota.addEdge(12, 17, 117); // pacitan - trenggalek
        graphKota.addEdge(13, 14, 28); // kediri - nganjuk
        graphKota.addEdge(13, 15, 31); // kediri - tulunggagung
        graphKota.addEdge(13, 16, 44); // kediri - blitar
        graphKota.addEdge(13, 18, 100); // kediri - malang
        graphKota.addEdge(15, 17, 32); // tulungagung - trenggalek
        graphKota.addEdge(15, 16, 33); // tulungagung - blitar
        graphKota.addEdge(16, 18, 78); // blitar - malang
        graphKota.addEdge(18, 19, 55); // malang - pasuruhan
        graphKota.addEdge(18, 21, 112); // malang - lumajang
        graphKota.addEdge(19, 20, 39); // pasuruhan - probolinggo
        graphKota.addEdge(20, 23, 95); // probolinggo - situbondo
        graphKota.addEdge(20, 22, 92); // probolinggo - bondowoso
        graphKota.addEdge(20, 24, 96); // probolinggo - jember
        graphKota.addEdge(20, 21, 46); // probolinggo - lumajang
        graphKota.addEdge(21, 24, 72); // lumajang - jember
        graphKota.addEdge(22, 23, 35); // bondowoso - situbondo
        graphKota.addEdge(22, 24, 32); // bondowoso - jember
        graphKota.addEdge(23, 25, 94); // situbondo - banyuwangi
        graphKota.addEdge(24, 25, 105); // jember - banyuwangi
        graphKota.addEdge(26, 27, 62); // bangkalan - sampang
        graphKota.addEdge(26, 28, 96); // bangkalan - pamekasan
        graphKota.addEdge(27, 29, 85); // sampang - sumenep
        graphKota.addEdge(27, 28, 33); // sampang - pamekasan
        graphKota.addEdge(28, 29, 52); // pamekasan - sumenep

        do {
            menu();
            pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    graphKota.displayAdjacencyMatrix();
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Masukkan kota asal: ");
                    String startCity = input.next();
                    input.nextLine();
                    System.out.print("Masukkan kota tujuan: ");
                    String endCity = input.next();

                    int startIndex = graphKota.getIndexByCityName(startCity);
                    int endIndex = graphKota.getIndexByCityName(endCity);

                    if (startIndex != -1 && endIndex != -1) {
                        graphKota.shortPath(startIndex, endIndex);
                    } else {
                        System.out.println("Kota asal atau tujuan tidak valid.");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Terima Kasih");
                    input.close();
                    break;

                default:
                    System.out.println("Masukkan Pilihan Yang Ada\n");
                    break;
            }
        } while (pilihan != 3);
    }
}