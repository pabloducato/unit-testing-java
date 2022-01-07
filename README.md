```

## Command to run direct test class

## example 1

mvn -Dtest=AccountTest test

##Command to run direct mehod in the test class

## example 1

mvn -Dtest=AccountTest#test_checkWhetherNewAccountIsNotActive test

## example 2

mvn -Dtest=AccountTest#test_checkWhetherAccountWasActivated test

## Guidelines

The Hamcrest's is() matcher will throw NullPointerException when the expecattion is `null`

It's better to use the assertThat() -> nullValue() expected assertion rather than equalTo(null)

## hamcrest.org

Section -> Java -> API Documentation (JavaDoc) -> Latest Version -> JavaHamcrest X.Y

## Hamcrest -> MatcherAssert

## AssertJ -> Assertions

## https://joel-costigliola.github.io/assertj/

Section -> Quick Start -> Javadoc (3.x)

Matchers are very helpful and can correct the visibility, the readability, and clarity our unit tests

We encourage you to use the Hamcrest && Assertj matching libraties

## .gitignore templates

Site: https://github.com/github/gitignore

```


