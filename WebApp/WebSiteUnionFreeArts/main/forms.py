from django import forms


class ContactForm(forms.Form):
    Name = forms.CharField(required=True, label='Ваше имя')
    Email = forms.EmailField(required=True, label='Ваш e-mail')
    Topic = forms.CharField(required=True, label='Тема сообщения')
    Message = forms.CharField(
        required=True,
        widget=forms.Textarea,
        label='Сообщение'
    )