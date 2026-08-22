package com.libraryapplication.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LibraryLoggingAspect {

    private int totalVisitors = 0;

    @AfterReturning(
            pointcut = "execution(* com.libraryapplication.service.impl.BookServiceImpl.borrowBook(..)) || execution(* com.libraryapplication.service.impl.BookServiceImpl.returnBook(..))",
            returning = "result"
    )
    public void logBookStateChange(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("\n=================================================");
        System.out.println("[AOP LOG - THAY ĐỔI TRẠNG THÁI SÁCH]");
        if (methodName.equals("borrowBook")) {
            System.out.println("-> Hành động: MƯỢN SÁCH");
            System.out.println("-> ID Sách: " + args[0]);
            System.out.println("-> Mã phiếu mượn được cấp: " + result);
        } else {
            System.out.println("-> Hành động: TRẢ SÁCH");
            System.out.println("-> Mã phiếu trả thành công: " + args[0]);
        }
        System.out.println("=================================================\n");
    }

    @Before("execution(* com.libraryapplication.controller.BookController.*(..))")
    public void logLibraryVisitor(JoinPoint joinPoint) {
        totalVisitors++;
        String method = joinPoint.getSignature().toShortString();

        System.out.println("-------------------------------------------------");
        System.out.println("[AOP LOG - LƯỢT GHÉ THÂM THƯ VIỆN #" + totalVisitors + "]");
        System.out.println("-> Phương thức truy cập: " + method);
        System.out.println("-------------------------------------------------");
    }
}
