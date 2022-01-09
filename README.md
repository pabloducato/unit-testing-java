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

The Hamcrest's is() matcher will throw NullPointerException when the expecattion is `null`.

It's better to use the assertThat() -> nullValue() expected assertion rather than equalTo(null).

## hamcrest.org

Section -> Java -> API Documentation (JavaDoc) -> Latest Version -> JavaHamcrest X.Y

## Hamcrest -> MatcherAssert

## AssertJ -> Assertions

## https://joel-costigliola.github.io/assertj/

Section -> Quick Start -> Javadoc (3.x)

Matchers are very helpful and can correct the visibility, the readability, and clarity our unit tests.

We encourage you to use the Hamcrest && Assertj matching libraties.

## .gitignore templates

Site: https://github.com/github/gitignore

The default branch has been renamed!
master is now named main

If you have a local clone, you can update it by running the following commands.

git branch -m master main
git fetch origin
git branch -u origin/main main
git remote set-head origin -a

In JUnit 4 there were used annotations like @BeforeClass and @AfterClass.

In JUnit 4 there was an annotation called @Ignore to disable single test case.

In JUnit 4 to create single parameterized test there was a need to create
a special test runner and special @Parameters annotation.

In JUnit 4 the equivalent for the @Tag annotation was the @Category annotation.
Inside it was provided the name of the class that it did want to categorize direct tests.
The @Tag annotation is much more better, beacuse we receive greater flexibility.
We can place @Tag annotations over both methods and classes.

```


