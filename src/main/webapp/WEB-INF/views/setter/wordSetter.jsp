<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<ol class="breadcrumb">
		<li><a href="index.html">SpiderPanel</a></li>
		<li class="active">Setter</li>
	</ol>
	<h1>过滤词汇设置</h1>
	<div class="row">
		<div class="col-md-6">
			<div class="templatemo-progress">
				<div class="list-group">
					<a href="#" onclick="getStopWordView()" class="list-group-item">
						<h4 class="list-group-item-heading">
							<strong>停用詞</strong>Stop Word
						</h4>
						<p title="点击编辑" class="list-group-item-text">${stopWord}</p>
					</a>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="templatemo-progress">
				<div class="list-group">
					<a href="#" onclick="getSelectWordView()" class="list-group-item">
						<h4 class="list-group-item-heading">
							<strong>选择词</strong>Select Word
						</h4>
						<p title="点击编辑" class="list-group-item-text">${selectWord}</p>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div id="setterArea"></div>