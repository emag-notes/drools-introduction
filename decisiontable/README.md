# decisiontable

自動車保険における保険料算出のサンプルで、デシジョンテーブルを利用します。

Drools の以下サンプルを持ってきています。

https://github.com/droolsjbpm/drools/tree/master/drools-examples/src/main/java/org/drools/examples/decisiontable

## 環境

JDK 8

## 実行

``` sh
./gradlew run-decisiontable
[...]
Cheapest possible
########################################
BASE PRICE IS: 120
DISCOUNT IS: 20
[...]
```

デシジョンテーブルは以下にあります。

https://github.com/emag/drools-introduction/blob/master/decisiontable/src/main/resources/drools/decisiontable/ExamplePolicyPricing.xls

デシジョンテーブルや[投入するデータ](https://github.com/emag/drools-introduction/blob/master/decisiontable/src/main/java/drools/decisiontable/App.java#L17-L26
)(Fact といいます)の値をいろいろ変更して、実行結果が変わることを確認してみてください。
