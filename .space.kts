job("Run tests") {
    startOn {
        gitPush {
            branchFilter {
                +"refs/heads/main"
                +Regex("feature")
            }
        }
    }
   container(displayName = "Run mvn test", "maven:3-openjdk-11") {
       shellScript {
           content = "mvn test"
       }
   }
}