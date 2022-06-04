## MyBlog

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



## REST API



### Post

id(PK)

title

author

createdAt

modifiedAt

content





### comment

id(PK)

PostId(FK)

content

author

createdAt

modifiedAt



| 기능           | Method | Endpoint              | return |
| -------------- | ------ |-----------------------| ------ |
| 전체게시물목록 | GET    | /posts                |        |
| 게시글작성     | POST   | /posts                |        |
| 게시글조회     | GET    | /posts/{postId}       |        |
| 게시글수정     | PUT    | /posts/{postId}       |        |
| 게시글삭제     | DELETE | /posts/{postId}       |        |
| 댓글목록조회   | GET    | /{postId}/comments    |        |
| 댓글 작성      | POST   | /{postId}/comments    |        |
| 댓글 수정      | PUT    | /comments/{commentId} |        |
| 댓글 삭제      | DELETE | /comments/{commentId} |        |
|                |        |                       |        |
|                |        |                       |        |

** 게시물을 삭제했을 때, 댓글 역시 삭제되어야 한다.

댓글을 삭제했을 때는 , 게시물이 삭제되지 않는다.

