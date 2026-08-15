<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lựa Chọn Gia Vị Sandwich</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- FontAwesome 6 Icons CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Google Fonts Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        html, body {
            width: 100vw;
            min-height: 100vh;
            margin: 0;
            padding: 0;
            background-color: #020617 !important;
            color: #f8fafc;
            font-family: 'Inter', sans-serif;
            overflow-x: hidden;
        }
    </style>
</head>
<body class="w-full min-h-screen flex items-center justify-center p-4">

<!-- Ambient Glow Background -->
<!-- Ambient Glow Background -->
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[500px] h-[500px] bg-emerald-500/15 rounded-full blur-[120px] pointer-events-none"></div>

<!-- Main Card Container -->
<div class="relative w-full max-w-lg bg-slate-900/90 backdrop-blur-2xl rounded-3xl border border-slate-800 shadow-2xl p-6 sm:p-8 space-y-6 z-10">

    <!-- Header -->
    <div class="text-center space-y-2">
        <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-gradient-to-tr from-emerald-500 to-teal-400 p-0.5 shadow-lg shadow-emerald-500/20">
            <div class="w-full h-full bg-slate-950 rounded-[14px] flex items-center justify-center">
                <i class="fa-solid fa-utensils text-2xl text-emerald-400"></i>
            </div>
        </div>
        <h1 class="text-2xl sm:text-3xl font-extrabold bg-gradient-to-r from-white via-slate-200 to-slate-400 bg-clip-text text-transparent">
            Gia Vị Đã Chọn
        </h1>
        <p class="text-xs sm:text-sm text-slate-400">
            Danh sách gia vị ăn kèm chiếc Sandwich của bạn
        </p>
    </div>

    <!-- Result Box -->
    <div class="space-y-4">
        <c:choose>
            <c:when test="${not empty selectedCondiments}">
                <div class="bg-gradient-to-br from-emerald-950/80 to-slate-900 border border-emerald-500/30 rounded-2xl p-5 space-y-3">
                    <div class="flex items-center gap-2 text-xs font-semibold text-emerald-400 uppercase tracking-wider">
                        <i class="fa-solid fa-circle-check"></i> Các gia vị đi kèm:
                    </div>

                    <div class="flex flex-wrap gap-2 pt-1">
                        <c:forEach var="item" items="${selectedCondiments}">
                            <span class="px-3 py-1.5 rounded-xl bg-emerald-500/20 border border-emerald-500/40 text-emerald-200 text-sm font-semibold flex items-center gap-2">
                                <i class="fa-solid fa-check text-xs text-emerald-400"></i>
                                ${item}
                            </span>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="bg-gradient-to-br from-amber-950/80 to-slate-900 border border-amber-500/30 rounded-2xl p-5 flex items-center gap-3">
                    <i class="fa-solid fa-triangle-exclamation text-amber-400 text-xl"></i>
                    <span class="text-sm font-medium text-amber-200">${message}</span>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Action Button -->
    <div class="pt-2">
        <a href="${pageContext.request.contextPath}/"
           class="w-full bg-slate-800/80 hover:bg-slate-800 text-slate-200 font-semibold py-3 px-4 rounded-xl border border-slate-700/80 transition duration-200 flex items-center justify-center gap-2 text-sm">
            <i class="fa-solid fa-rotate-left"></i>
            <span>Chọn lại gia vị khác</span>
        </a>
    </div>

    <div class="text-center pt-2 border-t border-slate-800">
        <p class="text-[11px] text-slate-500">
            Ứng dụng Spring MVC Sandwich Condiment Selection
        </p>
    </div>

</div>

</body>
</html>