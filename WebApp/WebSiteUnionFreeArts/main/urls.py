"""WebSiteUnionFreeArts URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from .views import main, about, technical_support, contact, comment

urlpatterns = [
    url(r'^$', main, name='main'),
    url(r'^about/$', about, name='about'),
    url(r'^technical_support/$', technical_support, name='technical_support'),
    url(r'^contact/$', contact, name='contact'),
    url(r'^comment/$', comment, name='comment'),
]
