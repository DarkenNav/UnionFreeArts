from django.test import TestCase
from django.core.urlresolvers import reverse


class TestMainPage(TestCase):

    def test_main_page(self):
        """ Тест на доступность главной страницы сайта
        """
        response = self.client.get(reverse('main'))
        self.assertEqual(response.status_code, 200,
                         msg='Главная страница работает неверно:'
                             ' не возвращает код 200 OK')

    def test_about_page(self):
        """ Тест на доступность страницы "О компании"
        """
        response = self.client.get(reverse('about'))
        self.assertEqual(response.status_code, 200,
                         msg='Страница "О компании" работает неверно:'
                             ' не возвращает код 200 OK')

    def test_technical_support_page(self):
        """ Тест на доступность страницы сайта "Техническая помощь"
        """
        response = self.client.get(reverse('technical_support'))
        self.assertEqual(response.status_code, 200,
                         msg='Страница "Техническая помощь" работает неверно:'
                             ' не возвращает код 200 OK')

    def test_contact_page(self):
        """ Тест на доступность страницы сайта "Связь"
        """
        response = self.client.get(reverse('contact'))
        self.assertEqual(response.status_code, 200,
                         msg='Страница "Связь" работает неверно:'
                             ' не возвращает код 200 OK')

    def test_comment_page(self):
        """ Тест на доступность страницы сайта "Отзывы"
        """
        response = self.client.get(reverse('comment'))
        self.assertEqual(response.status_code, 200,
                         msg='Страница "Отзывы" работает неверно:'
                             ' не возвращает код 200 OK')
