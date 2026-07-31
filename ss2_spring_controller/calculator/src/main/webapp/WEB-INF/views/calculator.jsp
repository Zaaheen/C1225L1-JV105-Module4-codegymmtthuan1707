<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Máy Tính Cá Nhân - Spring MVC Calculator</title>
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
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[500px] h-[500px] bg-sky-500/15 rounded-full blur-[120px] pointer-events-none"></div>

<!-- Main Card Container -->
<div class="relative w-full max-w-lg bg-slate-900/90 backdrop-blur-2xl rounded-3xl border border-slate-800 shadow-2xl p-6 sm:p-8 space-y-6 z-10">

    <!-- Header -->
    <div class="text-center space-y-2">
        <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-gradient-to-tr from-sky-500 to-indigo-500 p-0.5 shadow-lg shadow-sky-500/20">
            <div class="w-full h-full bg-slate-950 rounded-[14px] flex items-center justify-center">
                <i class="fa-solid fa-calculator text-2xl text-sky-400"></i>
            </div>
        </div>
        <h1 class="text-2xl sm:text-3xl font-extrabold bg-gradient-to-r from-white via-slate-200 to-slate-400 bg-clip-text text-transparent">
            Máy Tính Cá Nhân
        </h1>
        <p class="text-xs sm:text-sm text-slate-400">
            Ứng dụng tính toán các phép tính cơ bản với Spring MVC
        </p>
    </div>

    <!-- Calculator Form -->
    <form action="${pageContext.request.contextPath}/calculate" method="post" class="space-y-5">

        <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <!-- First Operand -->
            <div class="space-y-1.5">
                <label for="firstOperand" class="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
                    Số thứ nhất (First Number)
                </label>
                <input type="number" step="any" id="firstOperand" name="firstOperand"
                       value="${firstOperand != null ? firstOperand : ''}" required
                       placeholder="Nhập số thứ nhất..."
                       class="w-full px-4 py-3 bg-slate-800/80 border border-slate-700 rounded-xl text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-sky-500 text-sm font-medium">
            </div>

            <!-- Second Operand -->
            <div class="space-y-1.5">
                <label for="secondOperand" class="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
                    Số thứ hai (Second Number)
                </label>
                <input type="number" step="any" id="secondOperand" name="secondOperand"
                       value="${secondOperand != null ? secondOperand : ''}" required
                       placeholder="Nhập số thứ hai..."
                       class="w-full px-4 py-3 bg-slate-800/80 border border-slate-700 rounded-xl text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-sky-500 text-sm font-medium">
            </div>
        </div>

        <!-- Operator Selection Buttons -->
        <div class="space-y-1.5">
            <label class="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
                Chọn phép tính (Operator)
            </label>
            <div class="grid grid-cols-4 gap-2.5">
                <button type="submit" name="operator" value="+"
                        class="py-3.5 px-4 rounded-xl bg-slate-800 hover:bg-sky-600 active:bg-sky-700 border border-slate-700 hover:border-sky-500 text-slate-200 hover:text-white font-bold text-lg transition flex items-center justify-center gap-1">
                    <i class="fa-solid fa-plus text-sm"></i>
                </button>
                <button type="submit" name="operator" value="-"
                        class="py-3.5 px-4 rounded-xl bg-slate-800 hover:bg-sky-600 active:bg-sky-700 border border-slate-700 hover:border-sky-500 text-slate-200 hover:text-white font-bold text-lg transition flex items-center justify-center gap-1">
                    <i class="fa-solid fa-minus text-sm"></i>
                </button>
                <button type="submit" name="operator" value="*"
                        class="py-3.5 px-4 rounded-xl bg-slate-800 hover:bg-sky-600 active:bg-sky-700 border border-slate-700 hover:border-sky-500 text-slate-200 hover:text-white font-bold text-lg transition flex items-center justify-center gap-1">
                    <i class="fa-solid fa-xmark text-sm"></i>
                </button>
                <button type="submit" name="operator" value="/"
                        class="py-3.5 px-4 rounded-xl bg-slate-800 hover:bg-sky-600 active:bg-sky-700 border border-slate-700 hover:border-sky-500 text-slate-200 hover:text-white font-bold text-lg transition flex items-center justify-center gap-1">
                    <i class="fa-solid fa-divide text-sm"></i>
                </button>
            </div>
        </div>
    </form>

    <!-- Result / Error Display Box -->
    <c:if test="${not empty result || not empty errorMessage}">
        <div class="pt-2">
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <div class="bg-rose-950/80 border border-rose-500/40 rounded-2xl p-4 flex items-center gap-3 text-rose-200">
                        <i class="fa-solid fa-triangle-exclamation text-rose-400 text-xl shrink-0"></i>
                        <div class="text-xs font-medium">${errorMessage}</div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="bg-gradient-to-br from-sky-950/80 to-slate-900 border border-sky-500/40 rounded-2xl p-5 space-y-1">
                        <div class="text-xs text-sky-400 font-semibold uppercase tracking-wider flex items-center gap-1.5">
                            <i class="fa-solid fa-equals"></i> Kết quả phép tính:
                        </div>
                        <div class="text-2xl font-black text-white tracking-wide">
                                ${firstOperand} ${operator} ${secondOperand} = <span class="text-sky-300">${result}</span>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>

    <!-- Footer -->
    <div class="text-center pt-2 border-t border-slate-800">
        <p class="text-[11px] text-slate-500">
            Ứng dụng Spring MVC Calculator
        </p>
    </div>

</div>

</body>
</html>