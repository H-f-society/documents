# [Home](../README.md)

### 命令
```shell
	git rm -r --cached .
	git status
	git branch
	git add *
	git commit -m "update"
	git pull
	git push origin master
```

### 获取最近一次修改的文件
```shell
	git diff --name-only head~
```

### 分支管理
```shell
	# 创建分支
	git branch -b sit
	# 拉取分支
	git fetch branch sit
	# 切换分支
	git checkout sit	
	# 分支保护
	git config merge.ours.driver true
	a.txt merge=ours
	# 合并分支
	git merge dev
```