from django import forms


class ContactForm(forms.Form):
    Имя = forms.CharField(required=True)
    Email = forms.EmailField(required=True)
    Тема = forms.CharField(required=True)
    Сообщение = forms.CharField(
        required=True,
        widget=forms.Textarea
    )