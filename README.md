# QnA service

## Objective
To design a Stack Overflow like service that allows users to interact through a Question and Answer social platform

## Functional requirement
1. Top questions are to be shown in the home page 
2. Users can create a profile 
3. Users can post a question, tag a question 
4. Users can answer to a question. Rich media content (photos/videos) can  be added as an answer. 
5. Users can answer to an answer. 
6. Users can vote to an answer or question 
7. User can search the tags and browse the questions by tags 
8. Users can search questions/answers by text 

## Non-functional requirements
1. System should be highly available
2. System should have low latency
3. Data must be consistent

## Assumptions:
1. Any media files (image, video etc.) will be uploaded on Amazon CDN.
2. Will use elastic search for search question/answer by text to improve retrieval response time
3. Only registered users can post, upvote, downvote to questions and answers. Authors can only update questions. 
4. Viewing content is open for all

## API
1. Retrieve top questions
2. Create question
3. Create answer

### Strategy
Assume a read-write ratio of 100:1, we will keep a separate application server for read and write. Write will perform on primary db node. 
Read will perform on replica server. Primary to replica will be asynchronous sync

## Technology stack
1. Backend/API - Springboot
2. Database - Postgres, Cassandra (by design)
3. Cache - Redis

## Data model diagram

![Data model](https://github.com/manibhushan05/stackoverflow/blob/master/datamodel.png?raw=true)
