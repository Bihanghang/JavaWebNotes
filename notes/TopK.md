### 求第二大数的两种求法：
```java
/**
 * 第一种解法有点暴力的意思。
 * */
public static int Second(int[] arr){
		int first = 0,second=0,three=0;
		for(int i =0;i<arr.length;i++){
			if (arr[i] >= first) {
				second =first;
				first=arr[i];
			} else if(arr[i] >= second){
				second = arr[i];
			}
		}
		return second;
    }
```
```java
/*
    第二种利用大根堆，不过要重写比较器。
*/
public static void main(String[] args) {
		PriorityQueue<Integer> queue=new PriorityQueue<>((first,second)->{
			if(first<second){
				return 1;
			}else if(first > second){
				return -1;
			}else{
				return 0;
			}
		});
		queue.offer(2);
		queue.offer(8);
		queue.offer(19);
		queue.offer(3);
		int[] arr = {2,5,6,9,10,22};
		for (int i = 0; i < arr.length; i++) {
			queue.add(arr[i]);
		}
		queue.poll();
		System.out.println(queue.peek());
    }
```