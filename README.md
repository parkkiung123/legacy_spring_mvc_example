## 그냥 참고용
혹시 나중에 legacy spring mvc를 다룰 수도 있으니 기본적인 것 정리해 놓음.

## 기능
로그인 페이지에서 로그인 하면 학생 성적을 보여줌.<br>
학생으로 로그인 하면 자기 성적만 나오고, 선생으로 로그인 하면 모든 학생 성적이 나옴.<br>
로그아웃하면 인터셉터가 감지하여 로그인 페이지로 이동.<br>
404등 에러가 발생하면 에러페이지로 이동.<br>

## xml설정
- 데이터소스 (오라클 jdbc:oracle:thin:@localhost:1521:xe)
- MyBatis세션 팩토리 (mybatis-config.xml : typeAlias, mappers/*.xml)
- 트랜잭션 (@Transactional)
- AOP설정 (@Before만 쓰고 있음)
- 뷰리졸버
- 밸리데이션 (Bean Validation (JSR-303) validator, LoginForm 클래스)
- 인터셉터 (로그인한 사람만 성적 볼 수 있게)
- encodingFilter
- error-page

## 테스트
gradle test --tests "com.example.StudentControllerTest"

## DB
oracle xe DB를 docker에서 돌려서 테스트 함. 아래 테이블 정의.  
dorker 이미지는 docker hub의 gvenzl/oracle-xe  
```sql
CREATE TABLE "ADMIN"."STUDENT" (
    "ID"      NUMBER,
    "NAME"    VARCHAR2(100 BYTE),
    "CLASS"   NUMBER,
    "TEACHER" VARCHAR2(100 BYTE),
    "KOREAN"  NUMBER,
    "ENGLISH" NUMBER,
    "MATH"    NUMBER,
    "SCIENCE" NUMBER,
    "HISTORY" NUMBER,
    PRIMARY KEY ("ID")
);
```
```sql
CREATE TABLE "ADMIN"."TEACHER" (
    "ID"    NUMBER,
    "NAME"  VARCHAR2(100 BYTE),
    "CLASS" NUMBER,
    PRIMARY KEY ("ID")
);
```


MIT License

Copyright (c) [2025] [parkkiung123]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.
