# twittercbr

Twitter のサンプルストリームから特定条件に合致するツイートを抽出するサンプルです。

Drools の以下サンプルを持ってきています。

https://github.com/droolsjbpm/droolsjbpm-contributed-experiments/tree/master/twittercbr

## 環境

JDK 8

## 準備

オンライン版を実行する場合、Twitter API への認証が必要です。

[dev.twitter.comにおけるアプリケーション登録、アクセストークン作成](https://github.com/yusuke/ideamugen#devtwittercom%E3%81%AB%E3%81%8A%E3%81%91%E3%82%8B%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E7%99%BB%E9%8C%B2%E3%82%A2%E3%82%AF%E3%82%BB%E3%82%B9%E3%83%88%E3%83%BC%E3%82%AF%E3%83%B3%E4%BD%9C%E6%88%90) を参考に、取得した API key、API secret、Access token、Access token secret を`src/main/resources/twitter4j.properties` に保存してください。

## 実行

ルールファイル(DRL)は `src/main/resources/drools/twittercbr/` 以下にあります。

### オンライン版

``` sh
./gradlew run-twittercbr -PfileName="twitterRules1.drl"
```

### オフライン版

あらかじめダンプしておいたストリームを利用します。API への認証は不要です。

``` sh
./gradlew run-twittercbr-offline -PfileName="twitterRules1.drl"
```

## 参考

* Twitter4JとDroolsでなんかリアルタイムな感じのもの - [tokobayashiの日記](http://d.hatena.ne.jp/tokobayashi/)
 * その1 http://d.hatena.ne.jp/tokobayashi/20111109#p1
 * その2 http://d.hatena.ne.jp/tokobayashi/20111110#p1
 * その3 http://d.hatena.ne.jp/tokobayashi/20111113#p1
