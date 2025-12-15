Mackerel オンラインハンズオン #1

# Mackerel ではじめる APM 入門

OpenTelemetry ゼロコード計装で「まずは見える」を実現する

> [!NOTE]
> このリポジトリは、Mackerel オンラインハンズオン #1 のデモアプリケーションです。
> 詳細は動画をご覧ください https://youtu.be/

## 事前準備

- JDK 17 以上
- 管理者権限を持つ Mackerel オーガニゼーション

## ハンズオン用リンク・コマンド集

### デモアプリケーションのクローン

```sh
git clone https://github.com/mackerelio/handson-get-start-apm.git
```

### デモアプリケーションのディレクトリに移動

```sh
cd handson-get-start-apm
```

### デモアプリケーションのビルド

```sh
./gradlew build
```

### デモアプリケーションの実行

```sh
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

### デモアプリケーション

http://localhost:8080

### Mackerel API キーの設定

macOS, Linux, WSL などの場合

```sh
export MACKEREL_APIKEY="Mackerel API キー"
```

Windows（PowerShell）の場合

```pwsh
$env:MACKEREL_APIKEY = "Mackerel API キー"
```

### Mackerel

https://mackerel.io

### OpenTelemetry Java Agent のダウンロード

```sh
curl -L -O https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
```

### OpenTelemetry Java Agent のリリースページ

https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/

### OpenTelemetry の設定

macOS, Linux, WSL などの場合

```sh
export OTEL_EXPORTER_OTLP_PROTOCOL="http/protobuf"
export OTEL_EXPORTER_OTLP_ENDPOINT="https://otlp-vaxila.mackerelio.com"
export OTEL_EXPORTER_OTLP_HEADERS="Accept=*/*,Mackerel-Api-Key=${MACKEREL_APIKEY}"
export OTEL_METRICS_EXPORTER="none"
export OTEL_LOGS_EXPORTER="none"
```

Windows（PowerShell）の場合

```pwsh
$env:OTEL_EXPORTER_OTLP_PROTOCOL = "http/protobuf"
$env:OTEL_EXPORTER_OTLP_ENDPOINT = "https://otlp-vaxila.mackerelio.com"
$env:OTEL_EXPORTER_OTLP_HEADERS = "Accept=*/*,Mackerel-Api-Key=${env:MACKEREL_APIKEY}"
$env:OTEL_METRICS_EXPORTER = "none"
$env:OTEL_LOGS_EXPORTER = "none"
```

### エージェントをアタッチしたデモアプリケーションの実行

```sh
java -javaagent:opentelemetry-javaagent.jar -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```
