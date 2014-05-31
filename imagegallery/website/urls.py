from django.conf.urls import patterns, url

from website import views

urlpatterns = patterns('',
	url(r'^$', views.first, name='first'),
	#url(r'^main/(?P<user_id>\d+)',views.main, name='main'),
	url(r'^main/', views.main, name='main'),
	url(r'^main_page/', views.main_page, name='main_page'),
	url(r'^load_images/', views.load_images, name='load_images'),
)