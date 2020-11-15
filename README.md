# replacer

## Description

This is a simple tool to replace values in a file based on key-value pairs in another file. The replacements will be made in a third file. It is useful to have tools like this for automated deployment scenarios.

## Usage

```mvn clean package``` to create replacer.jar in target folder. You can then copy the jar to wherever you want to invoke it.

```java -jar replacer.jar <key-value file> <original file> <new file>```

You can test the application by cloning the repo, running ```mvn clean package```, and then using the included sample testfiles.

```java -jar target\replacer.jar kvp.testfile original.testfile output.testfile```

## Notes

The key value file should be in the form

```
KEY1=VALUE1
KEY2=VALUE2
etc.
```

The file to replace values should have the keys listed in the form

```{{KEY1}} {{KEY2}}```

For example, if the key value file was

```
MYSQL_HOST=127.0.0.1
MYSQL_DATABASE=testdb
```

and the file to replace value in has the text

```Now I want to use the host {{MYSQL_HOST}} with database {{MYSQL_DATABASE}}.```

Then the output would be

```Now I want to use the host 127.0.0.1 with database testdb.```

## Future

I might add functionality to replace values in files based on environment variables, if needed.