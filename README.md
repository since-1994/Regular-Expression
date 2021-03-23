# Regular Expression

## Regex 테스트 하는 사이트

- [Regex 101](https://regex101.com)
- [gskinner](https://regexr.com)

## Regex tutorial 사이트

- [zvon](https://zvon.org/comp/r/tut-Regexp.html)

## Pattern, Matcher

## 찾고자 하는 텍스트 입력하기 (기본)

별도의 옵션을 전달하지 않으면 대소문자를 구분하여 정확히 일치하는 문자열이 있나 찾습니다.

## Carrot ^, Dollar $ (위치)

### ^

소스의 시작에 위치하는 문자를 찾습니다.

```java
    Pattern pattern = Pattern.compile("^Hello");
    Matcher matcher = pattern.matcher("Hello world");
    System.out.print(matcher.find());//true
```

### $

소스의 끝에 위치하는 문자를 찾습니다.

```java
    Pattern pattern = Pattern.compile("Hello$");
    Matcher matcher = Pattern.matcher("Hello world");
    System.out.print(matcher.find());//false
```

## Escape \

만약에 $가 소스의 시작에 위치하는 것을 찾고 싶다면 어떻게 할까요?
`^$`로 하면 될까요? 되지 않습니다. `$`는 소스의 끝에 위치하는 문자를 찾는 것을 의미하기 때문입니다. 이럴땐 바로 뒤에 위치하는 기호를 단순 문자로 처리해주는 Escape 문자 `\`를 사용하면 됩니다.

```java
    Pattern pattern = Pattern.compile("^\$");
    Matcher matcher = Pattern.matcher("$>><>");
    System.out.print(matcher.find());//true
```

```java
    Pattern pattern = Pattern.compile("\$$");
    Matcher matcher = Pattern.matcher(">><>$");
    System.out.print(matcher.find());//true
```

## Point .

`.`은 어떤 문자와도 매칭 됩니다. 예를들어 `...`은 길이가 3인 문자열을 포함하고 있는가를 찾게 됩니다. (일종의 조커라고 생각하자..)

```java
    Pattern pattern = Pattern.compile(".");
    Matcher matcher = Pattern.matcher(">><>$");
    System.out.print(matcher.find());//true
```

## Square brackets []

`[]`은 안에 적힌 문자열을 이루는 모든 문자중 하나라도 일치하는게 있다면 찾습니다. 주의사항으론 가장 먼저 일치하는 하나의 문자를 찾는다는 것입니다. 여러 문자를 동시에 찾지 않습니다.

```java
    Pattern pattern = Pattern.compile("[you]");
    Matcher matcher = Pattern.matcher("o---");
    System.out.print(matcher.find());//true
```

y, o, u중 o와 일치하는게 맨 앞에 있으므로 true를 return 한 것입니다.

## Range - with []

대문자, 소문자, 숫자에 대해 적용할 수 있며 범위를 나타내는 기호 입니다.

`[c-k]`와 `[cdefghijk]`는 동일한 효과를 갖습니다.

`[2-9]`와 `[23456789]`는 동일한 효과를 갖습니다.

`[2-9a-dB-F]`와 같이 여러개를 연달아 사용할 수도 있습니다.

## Carrat ^ with []

Carrot을 `[]`안에서 사용하게 되면 not의 의미를 갖게 됩니다.

`[^123]`은 1,2,3을 제외한 모든 문자를 의미하게 됩니다.

`[^a-d]`은 a, b, c, d를 제외한 모든 문자를 의미하게 됩니다.

## Sub pattern () , |

`(hh|asd|ase)` hh, asd, ase 중 일치하는게 있으면 순서대로 찾습니다. 이것을 활용하면 요일을 쉽게 나타낼 수 있습니다.

`(MON|FRI|TUES)DAY` MONDAY, FRIDAY, TUESDAY 중에 일치하는 것을 순서대로 찾게 됩니다.

## Quantifiers(수량자) \*, +, ?

### \*

앞에 있는 문자가 있을수도(여러개) 있고 없을 수도 있는 경우에 대해 찾습니다.

```java
    Pattern pattern = Pattern.compile("a*b");
    Matcher matcher = Pattern.matcher("b");
    System.out.print(matcher.find());//true

    Pattern pattern1 = Pattern.compile("a*b");
    Matcher matcher1 = Pattern.matcher("aab");
    System.out.print(matcher1.find());//true
```

### +

앞에 있는 문자가 최소한 1개는 존재하는 문자열을 찾습니다. 여러개가 있어도 가능합니다.

```java
    Pattern pattern = Pattern.compile("a+b");
    Matcher matcher = Pattern.matcher("b");
    System.out.print(matcher.find());//false

    Pattern pattern1 = Pattern.compile("a*b");
    Matcher matcher1 = Pattern.matcher("aab");
    System.out.print(matcher1.find());//true
```

### ?

앞에 있는 문자가 1개가 있거나 없는 문자열을 찾습니다.

```java
    Pattern pattern = Pattern.compile("a+b");
    Matcher matcher = Pattern.matcher("b");
    System.out.print(matcher.find());//true

    Pattern pattern1 = Pattern.compile("a*b");
    Matcher matcher1 = Pattern.matcher("aab");
    System.out.print(matcher1.find());//true
```

### \*?

앞에 있는 문자가 없어야합니다.

`r.*?`은 오직 r을 선택하는 것을 의미합니다. 근데 그러면 그냥 r을 쓰면 되지 않나요.

### +?

`r.+?`은 어떤 문자든 딱 1개 더 붙어야 합니다.

### ??

`r.??`은 r뒤에 어떤 문자든 없어야 합니다.

### (다른 선택자)?

다른 수량자 앞에 붙는 ?의 의미는 선택범위를 가능한 최소로 만들어 버립니다.

- pattern `<div>.+</div>`는 문자열 `<div>hello</div><div>world</div>` 전체를 선택합니다.

- pattern`<div>.+?</div>`는 문자열 `<div>hello</div><div>world</div>`에서 hello를 포함한 태그만 선택하게 됩니다.

## Quantifiers Curly brackets {}

`[A-Z]{5}`는 알파벳 대문자로 이루어진 길이가 5인 문자열을 찾습니다.

,를 이용하여 범위를 나타낼 수 있습니다. `.{1,3}`길이가 1 이상 3 이하인 문자열을 찾습니다.

`{3,}`은 길이 3이상을 의미합니다.

``

## character class

`\w`는 `[A-z0-9_]` 와 동일합니다. 알파벳 대소문자와 숫자 그리고 \_를 포함합니다.

`\W`는 `[^A-z0-9_]` 와 동일합니다.

`\d`는 `[0-9]`와 동일합니다.

`\D`는 `[^0-9]`와 동일합니다.

## word boundary

`\b`는 word boundary를 의미하며 단어의 시작과 끝을 표시하게 됩니다.

- pattern이 `\bcat`이고 문자 소스가 `cat concat` 이면 concat은 선택되지 않고 cat만 선택되게 됩니다.

- pattern이 `cat\b`이고 문자 소스가 `category concat`이면 category는 선택되지 않고 concat만 선택되게 됩니다.
