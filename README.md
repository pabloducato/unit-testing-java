```
##Command to run direct test class

##example 1

mvn -Dtest=AccountTest test

##Command to run direct mehod in the test class

##example 1

mvn -Dtest=AccountTest#test_checkWhetherNewAccountIsNotActive test

##example 2

mvn -Dtest=AccountTest#test_checkWhetherAccountWasActivated test

##Guidelines

The Hamcrest's is() matcher will throw NullPointerException when the expecattion is `null`

It's better to use the assertThat() -> nullValue() expected assertion rather than equalTo(null)

```


