def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/mvn/bin/mvn"

pipeline {
    agent any
    parameters {
        string(name: 'BROWSER', defaultValue: 'remote.chrome', description: 'Тип браузера')
        string(name: 'TAG', defaultValue: '@myTag', description: 'Выбор теста')
    }
    stages {
        stage('Build') {
            steps {
                sh "${mvn} clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dtype.browser=\"${params.BROWSER}\"" +
                        "${mvn} test -Dtype.browser=\"${params.TAG}\""
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}