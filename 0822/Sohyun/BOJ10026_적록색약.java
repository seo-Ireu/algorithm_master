import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ10026_적록색약 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[][] adj1;
	static int[][] adj2;
	static boolean[][] visited;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}}; //상하좌우
	static int[] answer;
	
	public static boolean is_valid_coord(int r,int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	public static void bfs(int r, int c, int target, int[][]a) {
		visited[r][c] = true;
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {r,c,target});
		
		while(!dq.isEmpty()) {
			int[] now = dq.poll();
			
			for(int[] d: delta) {
				int nr = now[0]+d[0];
				int nc = now[1]+d[1];
				
				if(is_valid_coord(nr,nc) && !visited[nr][nc]&&a[nr][nc]==target) {
					visited[nr][nc]=true;
					dq.add(new int[] {nr,nc,target});
				}
				
			}
		}
	
	}
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		adj1 = new int[N][N];
		adj2 = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split("");
			
			for(int j=0;j<N;j++) {
				
				if(input[j].equals("R")) {
					adj1[i][j] = 0;
					adj2[i][j] = 0;
				}else if(input[j].equals("G")) {
					adj1[i][j] = 1;
					adj2[i][j] = 0;
				}else { //B
					adj1[i][j] = 2;
					adj2[i][j] = 3;			
				}
			}
		}
		
		answer = new int[2];
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					bfs(i,j,adj1[i][j], adj1);
					answer[0]++;
				}
			}
		}
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					bfs(i,j,adj2[i][j],adj2);
					answer[1]++;					
				}
			}
		}
		System.out.println(answer[0]+" "+answer[1]);
	}

}
