# Задания

## Задание 1. Поиск ошибок в логах
Найти все строки со словом ERROR во всех логах в каталоге logs (включая logs/old) и сохранить их в файл errors.txt в корне проекта.

```bash
grep -r "ERROR" ./logs/
grep -r "ERROR" ./logs/ > errors.txt
cat errors.txt
```

## Задание 2. Архивация старых логов
Создать каталог archived/ в корне проекта и переместить туда все файлы из logs/old.

```bash
ls -R
mkdir -p ../archived
```

## Задание 3. Подсчёт размера логов
Посчитать общий размер каталога logs и записать результат в logs_size.txt.

```bash
du -sb logs > logs_size.txt
```

## Задание 4. Нахождение самого большого лог-файла
Найти самый большой файл в каталоге logs (без учёта подкаталогов) и записать его имя в файл biglog.txt.

```bash
ls logs/ -S 
head -n 1 > biglog.txt
```

## Задание 5. Подсчёт количества логов
Подсчитать количество файлов с расширением .log во всём каталоге logs и сохранить результат в log_count.txt.

```bash
find . -name "*.logs" 
find logs -name "*.logs"
find logs -name "*.logs" | wc -l
find logs -name "*.logs" | wc -l -> logs_count.txt
```

## Задание 6. Поиск конфигурационных параметров
Найти во всех config/*.conf строки, содержащие слово "host", и записать в host_params.txt.

```bash
 find config -name "*.conf" -exec grep "host" {} \;
  find config -name "*.conf"
find config -name "*.conf" | xargs
grep "host" find config -name "*.conf" | xar
gs 
cd config 
grep -Rin .
grep -Rin
 grep "host" -Rin .
 grep "host" -Ri .
```

## Задание 7. Создание резервного архива конфигов
Создать zip-архив config_backup.zip, содержащий все файлы из config/.

```bash
zip -r project_backup.zip config/*.conf log/*.logs logs/old/*.logs errors.txt  2>/dev/null
```

## Задание 8. Создание общего резервного архива
Создать zip-архив project_backup.zip, куда включить:
- все *.conf из config/
- все *.log из logs (включая old/)
- файл errors.txt (если он есть)

```bash
zip project_backup.zip config/*.conf logs/*.log logs/old/*.log
[ -f errors.txt ]
zip -u project_backup.zip errors.txt
```

## Задание 9. Очистка пустых строк в логах
Создать файл cleaned_app.log, содержащий содержимое app.log без пустых строк.

```bash
grep . app.log > cleaned_app.log
```

## Задание 10. Подсчёт количества строк в каждом конфиге
Создать файл conf_stats.txt с вида:
app.conf 12  
db.conf 8  
(где число — количество строк в файле)

```bash
wc -l config/*.conf 
grep -v total > conf_stats.txt
```


## Задача: Создать собственный архиватор
Напишите функцию, которая:

1. Принимает на вход **каталог с файлами** (например, `project_data/`) и путь к архиву (`archive.zip`).
2. Создает **ZIP-архив** всех файлов внутри каталога, сохраняя структуру подкаталогов.
3. Использует классы `FileInputStream`, `FileOutputStream` и `java.util.zip.ZipOutputStream`.
4. Обеспечивает корректное закрытие потоков и обработку исключений.
5. Добавить фильтр по расширению файлов, чтобы архивировать только `.txt` или `.log`.

### Требования:
- Не использовать сторонние библиотеки для архивирования (только стандартный API).
- Программа должна выводить в консоль список добавляемых файлов и их размер.

mkdir kotlin_archiver
cd kotlin_archiver
mkdir -p lesson9/scr/main/kotlin/ru.tbank.education.school.lesson9
mkdir test_data
cat > src/main/kotlin/Archiver.kt

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun main() {
val dir = File("project_data")
val zip = ZipOutputStream(FileOutputStream("archive.zip"))
    dir.listFiles()?.forEach { file ->
        if ((file.name.endsWith(".txt")) or (file.name.endsWith(".log"))) {
            zip.putNextEntry(ZipEntry(file.name))
            FileInputStream(file).use { it.copyTo(zip) }
        }
    }
    zip.close()
}