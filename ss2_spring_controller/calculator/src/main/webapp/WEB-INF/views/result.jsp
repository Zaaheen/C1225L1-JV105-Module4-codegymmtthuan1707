<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Kết Quả Tính Toán - Spring MVC Calculator</title>
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
        <i class="fa-solid fa-square-poll-vertical text-2xl text-sky-400"></i>
      </div>
    </div>
    <h1 class="text-2xl sm:text-3xl font-extrabold bg-gradient-to-r from-white via-slate-200 to-slate-400 bg-clip-text text-transparent">
      Kết Quả Phép Tính
    </h1>
    <p class="text-xs sm:text-sm text-slate-400">
      Kết quả chi tiết được xử lý từ Spring MVC Controller
    </p>
  </div>

  <!-- Result Box -->
  <div class="space-y-4">
    <c:choose>
      <c:when test="${not empty errorMessage}">
        <div class="bg-rose-950/80 border border-rose-500/40 rounded-2xl p-5 flex items-center gap-3 text-rose-200 shadow-xl">
          <i class="fa-solid fa-triangle-exclamation text-rose-400 text-2xl shrink-0"></i>
          <div>
            <div class="text-xs font-bold text-rose-300 uppercase tracking-wider mb-0.5">Lỗi tính toán</div>
            <div class="text-sm font-medium">${errorMessage}</div>
          </div>
        </div>
      </c:when>
      <c:otherwise>
        <div class="bg-gradient-to-br from-sky-950/80 to-slate-900 border border-sky-500/40 rounded-2xl p-6 space-y-4 shadow-xl">
          <div class="flex items-center justify-between text-xs font-semibold text-sky-400 uppercase tracking-wider border-b border-slate-800 pb-3">
            <span><i class="fa-solid fa-equals mr-1"></i> Chi tiết phép tính</span>
            <span class="bg-sky-500/20 px-2.5 py-1 rounded-md text-sky-300 border border-sky-500/30">Phép tính: ${operator}</span>
          </div>

          <div class="grid grid-cols-2 gap-3 text-xs text-slate-300">
            <div class="bg-slate-800/60 p-3 rounded-xl border border-slate-700/60">
              <span class="text-slate-400 block mb-1">Số thứ nhất:</span>
              <span class="text-base font-bold text-white">${firstOperand}</span>
            </div>
            <div class="bg-slate-800/60 p-3 rounded-xl border border-slate-700/60">
              <span class="text-slate-400 block mb-1">Số thứ hai:</span>
              <span class="text-base font-bold text-white">${secondOperand}</span>
            </div>
          </div>

          <div class="pt-2">
            <span class="text-xs text-slate-400 font-medium">Kết quả cuối cùng:</span>
            <div class="text-3xl font-black text-sky-300 mt-1 tracking-wide">
                ${result}
            </div>
          </div>
        </div>
      </c:otherwise>
    </c:choose>
  </div>

  <!-- Action Button -->
  <div class="pt-2">
    <a href="${pageContext.request.contextPath}/"
       class="w-full bg-slate-800 hover:bg-sky-600 active:bg-sky-700 text-slate-200 hover:text-white font-semibold py-3.5 px-4 rounded-xl border border-slate-700 hover:border-sky-500 transition duration-200 flex items-center justify-center gap-2 text-sm shadow-lg">
      <i class="fa-solid fa-rotate-left"></i>
      <span>Thực hiện phép tính khác</span>
    </a>
  </div>

  <!-- Footer -->
  <div class="text-center pt-2 border-t border-slate-800">
    <p class="text-[11px] text-slate-500">
      Ứng dụng Spring MVC Calculator
    </p>
  </div>

</div>

</body>
</html>