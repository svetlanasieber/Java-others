package examPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class SantasPresents_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String materialsInput = scanner.nextLine(); //"10 -5 20 15 -30 10".split(" ") -> ["10", "-5", "20"...]
        String magicsInput = scanner.nextLine();    //"40 60 10 4 10 0"

        Stack<Integer> materials = new Stack<>();
        Arrays.stream(materialsInput.split("\\s+"))
                .map(Integer::parseInt).forEach(materials::push);

        Queue<Integer> magics = Arrays.stream(magicsInput.split("\\s+"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toCollection(ArrayDeque::new));

      
        Map<String, Integer> presentsMap = new TreeMap<>();
        presentsMap.put("Doll", 0);
        presentsMap.put("Wooden train", 0);
        presentsMap.put("Teddy bear", 0);
        presentsMap.put("Bicycle", 0);

        while (!materials.isEmpty() && !magics.isEmpty()) {
      
            int material = materials.peek();
            int magic = magics.peek();

            int totalMagic = magic * material; //получената магия

          
            if (totalMagic == 150) {
              
                materials.pop();
                magics.poll();
                presentsMap.put("Doll", presentsMap.get("Doll") + 1);
            } else if (totalMagic == 250) {
             
                materials.pop();
                magics.poll();
                presentsMap.put("Wooden train", presentsMap.get("Wooden train") + 1);
            } else if (totalMagic == 300) {
             
                materials.pop();
                magics.poll();
                presentsMap.put("Teddy bear", presentsMap.get("Teddy bear") + 1);
            } else if (totalMagic == 400) {
             
                materials.pop();
                magics.poll();
                presentsMap.put("Bicycle", presentsMap.get("Bicycle") + 1);
            } else if (totalMagic < 0) {
                int sum = material + magic;
                materials.pop();
                magics.poll();
                materials.push(sum);
            } else if (totalMagic > 0) {
                magics.poll();
                materials.push(materials.pop() + 15);
            } else {
               if (magic == 0) {
                   magics.poll();
               }
               if(material == 0) {
                   materials.pop();
               }
            }
        }

    
        boolean isDollAndTrain = presentsMap.get("Doll") > 0 && presentsMap.get("Wooden train") > 0;
        boolean isBearAndBicycle = presentsMap.get("Teddy bear") > 0 && presentsMap.get("Bicycle") > 0;
        if (isDollAndTrain || isBearAndBicycle) {
          
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
          
            System.out.println("No presents this Christmas!");
        }

     
        if (!materials.isEmpty()) {
            System.out.print("Materials left: ");
            //[3, 4, 5]
            Collections.reverse(materials);
            System.out.println(materials.toString().replace("[", "").replace("]", ""));
        }

      
        if (!magics.isEmpty()) {
            System.out.print("Magic left: ");
            System.out.println(magics.toString().replace("[", "").replace("]", ""));
        }

        for (Map.Entry<String, Integer> entry : presentsMap.entrySet()) {

            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

    }
}
