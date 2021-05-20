
create role intuit with password 'intuit' login createdb;
CREATE DATABASE "stackoverflowdb" WITH OWNER "intuit" ENCODING 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8' TEMPLATE template0;


API:
1. create question: /api/v1/questions POST
2. update question: /api/v1/questions PUT
3. questions index order by score: /api/v1/questions GET
4. questions by tag: /api/v1/questions/tagged/<tag_name>
5. show question and their associated answers: /api/v1/questions/<question_id> 
6. Answer a question: /api/v1/questions/<question_id>/answer POST
7. Answer an answer: /api/v1/answers/<answer_id> POST
8. 