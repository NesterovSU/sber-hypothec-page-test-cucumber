def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/mvn/bin/mvn"

pipeline {
    agent any
    parameters {
        string(name: 'TAG', defaultValue: '@myTag', description: 'Выбор теста')
        choice(name: 'BROWSER', choices: ['remote.chrome', 'remote.firefox'], description: 'Выбор браузера')
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