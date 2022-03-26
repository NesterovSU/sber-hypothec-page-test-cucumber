def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

node{
    stage('CheckoutSCM'){
        checkout(
                [$class: 'GitSCM',
                branches:[[name: 'refs/heads/${BRANCH}']],
                userRemoteConfigs:[[url:'https://github.com/NesterovSU/sber-hypothec-page-test-cucumber.git']]]
        )
    }
    stage('Build'){
        sh "${mvn} clean compile"
    }
    stage('Run Tests'){
        try{
            sh '${mvn} test -Dbrowser.type=\"${BROWSER}\"'
        }
        catch (Exception ex){
            echo "Test run was broken"
            throw ex
        }
        finally {
            stage('Allure Report Generation'){
                allure includeProperties: false,
                jdk:'',
                results: [[path:'target/reports/allure-results']]
            }
            cleanWs notFailBuild: true
        }
    }
}