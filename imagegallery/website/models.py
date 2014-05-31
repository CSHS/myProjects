from django.db import models

# Create your models here.

class User(models.Model):
		email = models.CharField(max_length=200)
		password = models.CharField(max_length=200)
		pub_date = models.DateTimeField('date published')
		def __str__(self):
			return self.email

class Image(models.Model):
	user = models.ForeignKey(User)
	image_url = models.CharField(max_length=200)
	def __str__(self):
		return self.image_url