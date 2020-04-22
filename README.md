Application deploy
1. Run/Install Docker Compose
2. cd  ${project_dir}/db
3. sudo docker-compose up postgres
4. Open http://localhost:8080/swagger-ui.html
5. Project have 3 preconfigured users: admin, user1 and user2 with passwords admin, user1 and user2 respectively.

Taken decisions:
1. Not to use swagger codegen to create a test UI from given swagger file for this project as it generates code 
that cannot be manually changed (or so it said in the manual), and readability of it isn't good enough.