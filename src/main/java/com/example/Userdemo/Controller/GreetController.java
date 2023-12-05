package com.example.Userdemo.Controller;
    import org.springframework.web.bind.annotation.*;

import java.io.*;

    @RestController
    @RequestMapping("/greeting")
    public class GreetController {

        int currentCount;
        int prevCount;
        File messageFile = new File("message.txt");
        File logFile = new File("log.txt");

        @PostMapping("/post")
        public String postMessage(@RequestBody String message){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(messageFile,true))){
                bw.write(message + "#");
                currentCount++;

            }
            catch(IOException e){
                System.out.println(e.getMessage());

            }
            return "message successful";
        }

        @GetMapping
        public String readFromFile(){
            StringBuilder stringBuilder = new StringBuilder();
            try(BufferedReader br = new BufferedReader(new FileReader(messageFile))){
                String x;
                while((x = br.readLine()) != null){
                    stringBuilder.append(x);
                }

            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
            return stringBuilder.toString();
        }



        @GetMapping("/count")
        public  int getCount(){
            return currentCount;
        }


        @GetMapping("/log")
        public int writeToLog(){
            while(prevCount < currentCount){
                try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile))){
                    bufferedWriter.write("New message created");
                    prevCount++;
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
            return prevCount;

        }






    }

