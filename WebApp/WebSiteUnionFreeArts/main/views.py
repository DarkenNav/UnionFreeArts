from django.shortcuts import render


def main(request):
    '''Главная страница сайта'''
    context = {}
    return render(request, 'index.html', context)


def about(request):
    '''Страница "О компании"'''
    context = {}
    return render(request, 'about.html', context)


def technical_support(request):
    '''Страница "Техническая помощь"'''
    context = {}
    return render(request, 'technical_support.html', context)


def contact(request):
    '''Страница "Связь"'''
    context = {}
    return render(request, 'contact.html', context)


def comment(request):
    '''Страница "Отзывы"'''
    context = {}
    return render(request, 'comment.html', context)
