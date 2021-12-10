job("Run tests") {
   container("maven:3-openjdk-11") {
       shellScript {
           content = "mvn test"
       }
   }
}