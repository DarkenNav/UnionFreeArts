from django.shortcuts import render
from .forms import ContactForm
from django.core.mail import EmailMessage
from django.shortcuts import redirect
from django.template.loader import get_template


def main(request):
    '''Главная страница сайта'''
    context = {}
    return render(request, 'index.html', context)


def about(request):
    '''Страница "О компании"'''
    context = {}
    return render(request, 'about.html', context)


def technical_support(request):
    form_class = ContactForm
    # new logic!
    if request.method == 'POST':
        form = form_class(data=request.POST)

        if form.is_valid():
            contact_name = request.POST.get('Name', '')
            contact_email = request.POST.get('Email', '')
            topic = request.POST.get('Topic', '')
            message = request.POST.get('Message', '')

            # Email the profile with the
            # contact information
            template = get_template('contact_template.txt')
        context = {
            'contact_name': contact_name,
            'contact_email': contact_email,
            'topic': topic,
            'message': message,
        }
        content = template.render(context)

        email = EmailMessage("New contact form submission", content, "Your website" + '',
                             ['youremail@gmail.com'],
                             headers={'Reply-To': contact_email}
                             )
        email.send()
        return render(request, 'form_success_tech_sup.html', {'form': form_class, })

    return render(request, 'technical_support.html', {'form': form_class, })


def contact(request):
    form_class = ContactForm
    # new logic!
    if request.method == 'POST':
        form = form_class(data=request.POST)

        if form.is_valid():
            contact_name = request.POST.get('Name', '')
            contact_email = request.POST.get('Email', '')
            topic = request.POST.get('Topic', '')
            message = request.POST.get('Message', '')

            # Email the profile with the
            # contact information
            template = get_template('contact_template.txt')
        context = {
            'contact_name': contact_name,
            'contact_email': contact_email,
            'topic': topic,
            'message': message,
        }
        content = template.render(context)

        email = EmailMessage("New contact form submission", content, "Your website" + '',
                             ['youremail@gmail.com'],
                             headers={'Reply-To': contact_email}
                             )
        email.send()
        return render(request, 'form_success_contact.html', {'form': form_class, })

    return render(request, 'contact.html', {'form': form_class, })


def comment(request):
    '''Страница "Отзывы"'''
    context = {}
    return render(request, 'comment.html', context)



