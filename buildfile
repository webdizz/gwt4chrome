# Generated by Buildr 1.4.0, change to your liking
# Version number for this release
VERSION_NUMBER = "0.2"
# Group identifier for your projects
GROUP = "name.webdizz.gwt"
COPYRIGHT = "Google with additions by Izzet Mustafaiev"

GWT_GROUP = 'com.google.gwt'
GWT_VERSION = '2.0.4'
GWT = [
	"#{GWT_GROUP}:gwt-dev:jar:#{GWT_VERSION}",
	"#{GWT_GROUP}:gwt-servlet:jar:#{GWT_VERSION}",
	"#{GWT_GROUP}:gwt-user:jar:#{GWT_VERSION}",
        'com.googlecode.mvp4g:mvp4g:jar:1.1.0',
        'com.google.gwt.google-apis:gwt-language:jar:1.1.0',
        'com.googlecode.gwt-mobile-webkit:htnl5-database:jar:1.5.1'
]
POWERMOCK_VERSION = '1.4'
TEST = [
	'org.mockito:mockito-all:jar:1.8.5',
	'junit:junit:jar:4.8.2',
	"org.powermock:powermock-mockito-release-full:jar:full:#{POWERMOCK_VERSION}",
	'javassist:javassist:jar:3.13.0.GA'
]

desc "gwt4chrome the library to build GWT based Google Chrome extensions"
define "gwt4chrome" do

  project.version = VERSION_NUMBER
  project.group = GROUP
  manifest["Implementation-Vendor"] = COPYRIGHT

  compile.with GWT
  test.with TEST
  package :jar

end
