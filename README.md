### JPA 사용을 위한 설정

- plugin.spring : 코틀린은 기본적으로 private이기 때문에 상속을 위해 open이 필요함
  - plugin.spring은 @Component, @Async, @Transactional, @Cacheable, SpringBoot 에 대해서만 open을 하며 기본적인 JPA는 잘 동작함
  - 하지만 JPA Auditing, @MappedSuperclass 등을 사용할 때 문제가 발생하므로 아래와 같이 추가적으로 open이 필요함
    ```
    allOpen {
      annotation("javax.persistence.Entity")
      annotation("javax.persistence.MappedSuperclass")
      annotation("javax.persistence.Embeddable")
    }
    ```

- plugin.jpa : java의 기본 생성자를 기본적으로 만들어 줌
  - @Entity, @Embeddable, @MappedSuperclass
  - (참고) 추가적으로 필요한 경우 아래와 같이 설정할 수 있음
    ```
    noArg {
      annotation("com.my.Annotation")
    }
    ```
[참고] https://sabarada.tistory.com/182


### Coding Style
- jlleitschuh-gradle-ktlint 사용
- .editorconfig 파일을 기반으로 스타일 검사를 진행
    ```
    # 스타일 검사 진행
    ./gradlew ktlintCheck

    # 자동 포맷팅 (되도록이면 직접 수정하는 것을 권장)
    ./gradlew ktlintFormat

    # git hook
    ./gradlew addKtlintCheckGitPreCommitHook # ktlinCheck를 pre-commit hook으로 등록
    # pre-commit hook 삭제
    rm .git/hooks/pre-commit
    ```
- IntelliJ 코드 스타일 적용
    ```
    # editorconfig을 InteeliJ 코드 스타일에 적용
    ./gradlew ktlintApplyToIde # 적용 시 cmd+opt+L로 자동 포맷팅 가능

    # Save Action 플러그인 사용
    [Preferences] - [Save Action]에서 상황에 맞게 적용
    ```

[참고] https://blog.benelog.net/ktlint.html

