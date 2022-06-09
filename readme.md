# MyBlog

간단한 게시글 목록 조회, 댓글 기능



## 요구사항

1. 전체 게시글 목록 조회
   - 제목, 작성자명, 작성 날짜를 조회하기
   - 작성 날짜 기준으로 내림차순 정렬하기
2. 게시글 작성
   - 제목, 작성자명, 작성 내용을 입력하기
3. 게시글 조회
   - 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
4. 게시글 수정
   - 제목, 작성자명, 작성 내용 중 원하는 내용을 수정하기
5. 게시글 삭제
   - 원하는 게시물을 삭제하기
   - *게시물을 삭제했을 때, 댓글 역시 삭제되어야 한다.*
6. 댓글 목록 조회
   - 조회하는 게시글에 작성된 모든 댓글을 목록 형식으로 볼 수 있도록 하기
   - 작성 날짜 기준으로 내림차순 정렬하기
7. 댓글 작성
   - 댓글 내용을 비워둔 채 댓글 작성 API를 호출하면 "댓글 내용을 입력해주세요" 라는 메세지를 return하기
   - 댓글 내용을 입력하고 댓글 작성 API를 호출한 경우 작성한 댓글을 추가하기
8. 댓글 수정
   - 댓글 내용을 비워둔 채 댓글 수정 API를 호출하면 "댓글 내용을 입력해주세요" 라는 메세지를 return하기
   - 댓글 내용을 입력하고 댓글 수정 API를 호출한 경우 작성한 댓글을 수정하기
9. 댓글 삭제
   - 원하는 댓글을 삭제하기
   
   - *댓글을 삭제했을 때는, 게시물이 삭제되지 않는다.*
   
     

## ERD
![erd](https://user-images.githubusercontent.com/17975647/172510863-e7383238-5a15-4679-aeac-00da769fb14e.png)




## REST API

| 기능             | Method | Endpoint              | return           |
| ---------------- | ------ | --------------------- | ---------------- |
| 전체 게시물 목록 | GET    | /posts                | ***List<Post>*** |
| 게시글 작성      | POST   | /posts                | ***Post***       |
| 게시글 조회      | GET    | /posts/{postId}       | ***Post***       |
| 게시글 수정      | PUT    | /posts/{postId}       | Long             |
| 게시글 삭제      | DELETE | /posts/{postId}       | Long             |
| 댓글 목록 조회   | GET    | /{postId}/comments    | ***Comment***    |
| 댓글 작성        | POST   | /{postId}/comments    | ***Comment***    |
| 댓글 수정        | PUT    | /comments/{commentId} | Long             |
| 댓글 삭제        | DELETE | /comments/{commentId} | Long             |



###  Response Example

#### Post

```json
{
    "createdAt": "2022-06-06T10:15:00",
    "modifiedAt": "2022-06-06T10:15:00",
    "postId": 5,
    "title": "2",
    "author": "2",
    "content": "2",
    "commentList": [
        {
            "createdAt": "2022-06-07T15:11:03",
            "modifiedAt": "2022-06-07T15:11:03",
            "id": 13,
            "post": 1,
            "content": "raw",
            "author": "test"
        },
        {
            "createdAt": "2022-06-07T15:11:00",
            "modifiedAt": "2022-06-07T15:11:00",
            "id": 12,
            "post": 1,
            "content": "raw",
            "author": "test"
        }
    ]
}
```

#### Comment

```json
 {
     "createdAt": "2022-06-07T15:11:03",
     "modifiedAt": "2022-06-07T15:11:03",
     "id": 123,
     "post": 1,
     "content": "raw",
     "author": "test"
 }
```

#### List<Comment>

```json
 [
     {
         "createdAt": "2022-06-07T15:11:03",
         "modifiedAt": "2022-06-07T15:11:03",
         "id": 123,
         "post": 1,
         "content": "raw",
         "author": "test"
     },
     {
         "createdAt": "2022-06-07T15:11:00",
         "modifiedAt": "2022-06-07T15:11:00",
         "id": 12,
         "post": 4,
         "content": "raw2",
         "author": "test2"
     }
 ]
```







## 예외 처리

#### 포스트, 댓글의 작동에는 어떤 예외 처리가 필요할까?

내가 생각할 수 있는 수준에서는 1️⃣**빈 값**, 2️⃣**유효하지 않은 값**이 들어오는 경우일 것 같다.



#### 1️⃣ 공백이 들어올 수 있는 경우

1. GET, POST, PUT, DELETE시의 **@PathVariable**

   - postId가 null & commentId가 null

     - ```@PathVariable(required = false) String board_id``` PathVariable의 default는 required=true이다. 혹시라도 null값을 받고 따로 처리하고자 하는 경우는 required=false를 활용하면 된다.

     - ```http://localhost:8080/posts//```를 요청했을 시 404 Not Found error가 발생하게 된다. 이런 경우에 예외 처리가 필요할까? 👉 PathVariable의 특성상 null을 반환하는건 좋지 않다고 한다. 필요한 경우 @RequestParam을 사용하는 것이 더 적절하다고 한다.🙃

2. POST, PUT시의 **@RequestBody** 

   - Post (게시물)
     1.  Title(제목)이 비어있는 경우
     2.  Author(작성자)가 비어 있는 경우
     3.  Content(내용)이 비어 있는 경우
   - Comment (댓글)
     1.  Content(내용)이 비어있는 경우
     2.  Author(작성자)가 비어 있는 경우
```java
@Component
public class Validator {
    // comment - 댓글의 예외처리
    public static void validateComment(CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getAuthor().equals("")) {
            throw new MyException(ErrorCode.EMPTY_COMMENT_AUTHOR);
        }
        if (commentRequestDto.getContent().equals("")) {
            throw new MyException(ErrorCode.EMPTY_COMMENT_CONTENT);
        }
    }
	// post - 게시물 의 예외처리
    public static void validatePost(PostRequestDto postRequestDto) {
        if (postRequestDto.getAuthor().equals("")) {
            throw new MyException(ErrorCode.EMPTY_POST_AUTHOR);
        }
        if (postRequestDto.getTitle().equals("")) {
            throw new MyException(ErrorCode.EMPTY_POST_TITLE);
        }
        if (postRequestDto.getContent().equals("")) {
            throw new MyException(ErrorCode.EMPTY_POST_CONTENT);
        }
    }
}
```

발생하는 Exception은 따로 exception package에 들어있다.




#### 2️⃣ 유효하지 않은 값이 들어오는 경우

1. 우리가 원하는 형식의 값이 들어오지 않거나 (Long을 받고 싶은데 String이 들어왔을 때 / 양수여야 하는 id값에 음수가 들어왔을 때),

2. DB 조회 시 커버하는 범위 외의 값이 들어왔을 때이다 (ex : 존재하지 않는 postId)

1의 경우 단순히 DTO에서 Validator를 써주면 되는데, 2의 경우는 repository를 따로 불러와서 조회해보는 일까지 거쳐야 한다..

일단 간단하게 숫자가 아닌 값을 막는 방법은 정규표현식을 사용하는 것이다.

GET /spring/comment 같이 문자열이 들어가는 경우 404를 호출하지 못하고 빈 페이지를 보여주었기 때문이다

```java
@GetMapping("/{postId:[0-9]*}/comments")
//{id:[A-Z]*} 식으로도 사용가능
public List<Comment> getComments(@PathVariable Long postId) {
    Post myPost = postService.findById(postId);
    return myPost.getCommentList();
}
```

이렇게 수정한 뒤에는 일단 404 를 잘 뱉어주긴 한다..



