#./manage.py collectstatic
#./manage.py runserver [::1]:8000
python3 ./manage.py runserver


# Создание проекта:
# django-admin startproject <имя проекта>
#Создание приложения:
# python3 manage.py startapp <название приложения>
# Создать суперпользователя:
#./manage.py createsuperuser

# Применение изменений в БД:
#./manage.py makemigrations app_main
##./manage.py migrate app_main
#./manage.py migrate/manage.py migrate

# Сохранение БД приложения indexapp в формате JSON с отступами 2 пробела:
#mkdir ./indexapp/fixtures
#./manage.py dumpdata app_main --indent 2 > ./app_main/fixtures/startdata.json

#Восстановление БД:
#./manage.py loaddata startdata.json


#Запускаем интерактивную консоль django:
#./manage.py shell


#test223