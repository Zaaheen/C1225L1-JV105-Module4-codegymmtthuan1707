<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>

<!-- FontAwesome 6 Icons CDN -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<!-- Google Fonts Inter -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<style>
  /* Sửa triệt để lỗi nền trắng và căn giữa khung hình */
  html, body {
    width: 100%;
    min-height: 100vh;
    margin: 0;
    padding: 0;
    background-color: #020617 !important; /* bg-slate-950 */
    color: #f8fafc;
    font-family: 'Inter', sans-serif;
    overflow-x: hidden;
  }
</style>


<!-- Background decorative blur lights -->
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[600px] h-[600px] bg-indigo-600/15 rounded-full blur-[120px] pointer-events-none"></div>
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[400px] h-[400px] bg-emerald-500/15 rounded-full blur-[100px] pointer-events-none"></div>

<!-- Main Card Centered Container -->
<div class="relative w-full max-w-lg mx-auto bg-slate-900/90 backdrop-blur-2xl rounded-3xl border border-slate-800 shadow-2xl p-6 sm:p-8 space-y-6 z-10">

  <!-- Header Section -->
  <div class="text-center space-y-2">
    <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-gradient-to-tr from-indigo-500 to-emerald-400 p-0.5 shadow-lg shadow-indigo-500/20">
      <div class="w-full h-full bg-slate-950 rounded-[14px] flex items-center justify-center">
        <i class="fa-solid fa-coins text-2xl text-emerald-400"></i>
      </div>
    </div>
    <h1 class="text-2xl sm:text-3xl font-extrabold bg-gradient-to-r from-white via-slate-200 to-slate-400 bg-clip-text text-transparent">
      Chuyển Đổi Tiền Tệ
    </h1>
    <p class="text-xs sm:text-sm text-slate-400">
      Quy đổi Đô la Mỹ (<span class="text-indigo-400 font-semibold">USD</span>) sang Đồng Việt Nam (<span class="text-emerald-400 font-semibold">VNĐ</span>)
    </p>
  </div>

  <!-- Result Display Card -->
  <div class="bg-gradient-to-br from-emerald-500 to-teal-700 rounded-2xl p-5 text-white shadow-xl shadow-emerald-900/30 relative overflow-hidden group transition-all duration-300">
    <div class="absolute -right-6 -bottom-6 text-9xl text-white/10 pointer-events-none transform group-hover:scale-105 transition-transform duration-500">
      <i class="fa-solid fa-money-bill-wave"></i>
    </div>

    <div class="flex items-center justify-between mb-2">
            <span class="text-emerald-100 text-xs font-semibold tracking-wider uppercase bg-emerald-900/40 px-2.5 py-1 rounded-full border border-emerald-400/30 flex items-center gap-1.5">
                <i class="fa-solid fa-circle-check text-emerald-300"></i> Kết quả quy đổi
            </span>
      <span class="text-emerald-200 text-xs flex items-center gap-1 font-medium">
                <i class="fa-solid fa-bolt text-amber-300"></i> Trực tiếp
            </span>
    </div>

    <div class="py-2">
      <div class="text-slate-200 text-xs font-medium mb-1">Thành tiền tương ứng</div>
      <div class="text-3xl sm:text-4xl font-black tracking-tight text-white flex items-baseline gap-2 flex-wrap">
        <span>${formattedVnd != null ? formattedVnd : '0'}</span>                     <span class="text-lg font-bold text-emerald-200">VNĐ</span>                 </div>             </div>              <!-- Detail stats row -->             <div class="mt-4 pt-3 border-t border-emerald-400/30 grid grid-cols-2 gap-2 text-xs text-emerald-100">                 <div class="bg-black/15 rounded-lg p-2.5 flex items-center gap-2.5">                     <i class="fa-solid fa-dollar-sign text-emerald-300 text-base"></i>                     <div>                         <div class="text-[10px] text-emerald-200/80 uppercase font-semibold">Số tiền gốc</div>                         <div class="font-bold text-white">$${formattedUsd != null ? formattedUsd : '0'} USD</div>
  </div>
  </div>
    <div class="bg-black/15 rounded-lg p-2.5 flex items-center gap-2.5">
      <i class="fa-solid fa-chart-line text-emerald-300 text-base"></i>
      <div>
        <div class="text-[10px] text-emerald-200/80 uppercase font-semibold">Tỷ giá áp dụng</div>
        <div class="font-bold text-white">${formattedRate != null ? formattedRate : '25,000'} VNĐ</div>
      </div>
    </div>
  </div>
  </div>

  <!-- Form Input Section -->
  <form action="${pageContext.request.contextPath}/calculate" method="post" class="space-y-4">

    <!-- Tỷ giá Input -->
    <div class="space-y-1.5">
      <label for="rate" class="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
        Tỷ giá quy đổi (VNĐ / 1 USD)
      </label>
      <div class="relative">
        <div class="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
          <i class="fa-solid fa-chart-line"></i>
        </div>
        <input type="number" step="any" min="0" id="rate" name="rate"
               value="${rate != null ? rate : 25000}" required
               placeholder="Ví dụ: 25000"
               class="w-full pl-10 pr-4 py-3 bg-slate-800/80 border border-slate-700/80 rounded-xl text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition duration-200 text-sm font-medium">
      </div>
    </div>

    <!-- Số tiền USD Input -->
    <div class="space-y-1.5">
      <label for="usd" class="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
        Số tiền USD muốn đổi ($)
      </label>
      <div class="relative">
        <div class="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
          <i class="fa-solid fa-dollar-sign"></i>
        </div>
        <input type="number" step="any" min="0" id="usd" name="usd"
               value="${usd != null ? usd : ''}" required
               placeholder="Ví dụ: 100"
               class="w-full pl-10 pr-4 py-3 bg-slate-800/80 border border-slate-700/80 rounded-xl text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition duration-200 text-sm font-medium">
      </div>
    </div>

    <!-- Action Buttons -->
    <div class="pt-2 space-y-2">
      <button type="submit"
              class="w-full bg-gradient-to-r from-emerald-500 to-teal-600 hover:from-emerald-400 hover:to-teal-500 text-slate-950 font-bold py-3 px-4 rounded-xl shadow-lg shadow-emerald-500/25 transition duration-200 transform hover:-translate-y-0.5 flex items-center justify-center gap-2 cursor-pointer">
        <i class="fa-solid fa-calculator text-slate-950"></i>
        <span>Chuyển Đổi Ngay</span>
      </button>

      <a href="${pageContext.request.contextPath}/"
         class="w-full bg-slate-800/60 hover:bg-slate-800 text-slate-300 font-semibold py-2.5 px-4 rounded-xl border border-slate-700/60 transition duration-200 flex items-center justify-center gap-2 text-xs">
        <i class="fa-solid fa-rotate-left"></i>
        <span>Nhập lại từ đầu</span>
      </a>
    </div>
  </form>

  <!-- Footer Note -->
  <div class="text-center pt-2 border-t border-slate-800">
    <p class="text-[11px] text-slate-500">
      Ứng dụng Spring MVC Currency Converter &bull; Hỗ trợ định dạng tiền tệ tự động
    </p>
  </div>

</div>
