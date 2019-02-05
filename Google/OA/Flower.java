import java.util.*;
public class Flower {
    public static void main(String[] args) {
        int[] flowers = new int[]{5, 3, 6, 7, 2, 1, 4};
        System.out.println(getLatestDay(flowers, 2, 1));
        int[] flowers2 = new int[]{1, 4, 2, 5, 3};
        System.out.println(firstDay(flowers2, 2));
    }

    private static int getLatestDay(int[] flowers, int M, int K) {
        List<Interval> slots = new ArrayList<>();
        int len = flowers.length;
        slots.add(new Interval(1, len));

        if (K > len) {
            return -1;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (slots.size() == M) {
                return i + 1;
            }
            int flower = flowers[i];
            for (int j = 0; j < slots.size(); j++) {
                Interval in = slots.get(j);
                if (in.start == flower) {
                    slots.get(j).start = flower + 1;
                    if (slots.get(j).getScope() < K) {
                        slots.remove(j);
                    }
                    break;
                }
                else if (in.end == flower) {
                    slots.get(j).end = flower - 1;
                    if (slots.get(j).getScope() < K) {
                        slots.remove(j);
                    }
                    break;
                }
                else if (in.start < flower && in.end > flower) {
                    slots.get(j).end = flower - 1;
                    slots.add(j + 1, new Interval(flower + 1, in.end));
                    break;
                }
            }
        }

        return -1;
    }

    private static int firstDay(int[] flowers, int k) {
        int len = flowers.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < flowers.length; i++) {
            map.put(flowers[i], i + 1);
        }
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, len));
        for (int i = 1; i <= len; i++) {
            int pos = map.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getScope() == k) {
                    return i - 1;
                }

                if (list.get(j).start == pos) {
                    list.get(j).start = pos + 1;
                    break;
                }
                else if (list.get(j).end == pos) {
                    list.get(j).end = pos - 1;
                    break;
                }
                else if (list.get(j).start < pos && list.get(j).end > pos) {
                    int temp = list.get(j).end;
                    list.get(j).end = pos - 1;
                    list.add(j + 1, new Interval(pos + 1, temp));
                    break;
                }
            }
        }
        return -1;
    }
}

class Interval {
    int start;
    int end;

    Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }

    int getScope() {
        return end - start + 1;
    }
}