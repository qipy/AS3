@echo off
::ת����ǰ�̷�
%~d0
::�򿪵�ǰĿ¼
cd %~dp0
::��������JAR����·��
set MainJar=AneSDK.jar
::������JAR����·��
set ExternalJar=oral.jar
::������JAR������������
set packageName=cn
echo =========== start combin ==============
::��ѹ��������
jar -xf %ExternalJar%
::�ϲ���JAR��
jar -uf %MainJar% %packageName% 
::������б�Ķ��������Խ��źϲ������磺
::jar -uf %MainJar% %packageName2%
echo =========== over ==============
echo �ٵ�һ�¾ͽ�����--СQ
pause