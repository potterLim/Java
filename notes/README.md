# Java 강의 노트

이 디렉터리는 Java 학습을 위한 강의 노트와 슬라이드에 등장하는 주요 코드 블록의 전체 형태를 함께 정리한 공간입니다.

강의 노트는 C 언어를 비롯한 다른 프로그래밍 언어의 기초 경험이 있는 학습자가 Java의 값과 참조, 문자열 처리, 컬렉션, 문법 편의성, 사용자 정의 자료형을 단계적으로 익힐 수 있도록 구성되어 있습니다.  
객체지향 설계 자체를 깊게 다루기보다 Java로 프로그램을 안정적으로 작성하는 데 필요한 개념과 코드 작성 방식을 설명하는 데 초점을 둡니다.

## 강의 노트 목록

| 번호 | 주제                        | 강의 노트      | 슬라이드 코드   |
| :--: | :-------------------------- | :------------: | :-------------: |
|  01  | 언어 분류와 설계 관점       | [PDF][note-01] |        -        |
|  02  | 값과 참조                   | [PDF][note-02] | [코드][code-02] |
|  03  | 표현력과 메서드 설계        | [PDF][note-03] | [코드][code-03] |
|  04  | 컬렉션을 활용한 데이터 관리 | [PDF][note-04] | [코드][code-04] |
|  05  | 문법과 언어 사용성          | [PDF][note-05] | [코드][code-05] |
|  06  | 사용자 정의 자료형          | [PDF][note-06] | [코드][code-06] |

## 슬라이드 코드

`slide-code/`는 강의 슬라이드에 등장하는 코드 블록을 전체 코드 형태로 확인하기 위한 보조 자료입니다.  
슬라이드에서는 지면 제약 때문에 `import`, `class`, `main` 메서드, 보조 타입 등이 생략될 수 있습니다.

슬라이드 코드는 모든 슬라이드에 대응하지 않습니다.  
직접 실행하거나 전체 문맥을 확인할 필요가 있는 코드만 선별하여 정리합니다.

장 전체의 개념을 하나의 흐름으로 묶어 보는 통합 예제는 [`example-code/`](../example-code)를 참고합니다.

## 학습 순서

1. 강의 노트 PDF를 먼저 읽으며 핵심 개념을 이해한다.
2. 필요한 경우 `slide-code/`에서 슬라이드 코드의 전체 형태를 확인한다.
3. [`example-code/`](../example-code)를 통해 각 장의 개념이 실제 코드 흐름으로 어떻게 연결되는지 확인한다.
4. [`lab/`](../lab)의 실습을 통해 직접 구현하며 개념을 적용한다.

[note-01]: ./01-language-classification-and-design-perspectives.pdf
[note-02]: ./02-values-and-references.pdf
[note-03]: ./03-expressiveness-and-method-design.pdf
[note-04]: ./04-managing-data-with-collections.pdf
[note-05]: ./05-syntax-and-language-ergonomics.pdf
[note-06]: ./06-user-defined-types.pdf

[code-02]: ./slide-code/02-values-and-references/
[code-03]: ./slide-code/03-expressiveness-and-method-design/
[code-04]: ./slide-code/04-managing-data-with-collections/
[code-05]: ./slide-code/05-syntax-and-language-ergonomics/
[code-06]: ./slide-code/06-user-defined-types/
