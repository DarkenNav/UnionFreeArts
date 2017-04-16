from django.shortcuts import render


def main(request):
    '''Главная страница сайта'''
    context = {}
    return render(request, 'index.html', context)


def about(request):
    '''Страница "О компании"'''
    context = {}
    return render(request, 'about.html', context)
