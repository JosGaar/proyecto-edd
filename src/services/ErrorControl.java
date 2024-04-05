package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorControl {

    public int validateNumericInputInt(Scanner input, String message)
    {
        int number;
        while (true)
        {
            try
            {
                System.out.print(message);
                number = Integer.parseInt(input.nextLine());
                if (number < 0)
                {
                    throw new IllegalArgumentException("Error: El número no puede ser negativo.");

                }
                return number;
            } catch (NumberFormatException e)
            {
                System.err.println("Error: Por favor ingrese un número válido.\n");
            } catch (IllegalArgumentException e)
            {
                System.err.println("Error: Por favor ingrese un número válido.\n");
            }
        }
    }

    public String validateIDNumber(Scanner input, String message)
    {
        String idNumber;
        while (true)
        {
            System.out.print(message);
            idNumber = input.nextLine();
            if (idNumber.matches("\\d{10}"))
            {
                return idNumber;
            } else
            {
                System.err.println("Error: La cédula debe tener 10 números.\n");
            }
        }
    }

    public String validateTwoWords(Scanner input, String message, String categoria)
    {
        String names;
        while (true)
        {
            System.out.print(message);
            names = input.nextLine().trim().replaceAll("\\s+", " ");

            String[] words = names.split(" ");

            if (words.length == 2)
            {
                return names;
            } else
            {
                System.err.println("Error: por favor ingrese dos " + categoria + " separados por un espacio.\n");
            }
        }
    }

    public String validateStrings(Scanner input, String message)
    {
        String word;
        while (true)
        {
            System.out.print(message);
            word = input.nextLine();

            Pattern pattern = Pattern.compile("^[a-zA-Z0-9,](?:[a-zA-Z0-9, ]*[a-zA-Z0-9,])?$");
            Matcher matcher = pattern.matcher(word.trim());

            if (matcher.matches())
            {
                return word.trim().replaceAll("\\s+", " ");
            } else
            {
                System.err.println("Error: por favor ingrese un texto valido.\n");
            }
        }
    }

    public String validatePhoneNumber(Scanner input, String message)
    {
        String phoneNumber;
        while (true)
        {
            System.out.print(message);
            phoneNumber = input.nextLine();

            if (phoneNumber.matches("\\d+"))
            {
                if (phoneNumber.length() == 10)
                {
                    return phoneNumber;
                } else
                {
                    System.err.println("N�mero de tel�fono inv�lido. Debe tener exactamente 10 d�gitos.\n");
                }
            } else
            {
                System.err.println("N�mero de tel�fono inv�lido. Ingrese solo n�meros.\n");
            }
        }
    }

    public LocalDateTime validateLocalDateTime(Scanner input, String message)
    {
        String date;
        while (true)
        {
            System.out.print(message);
            date = input.nextLine();

            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(date);

            if (matcher.matches())
            {
                try
                {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                    return dateTime;
                } catch (DateTimeParseException e)
                {
                    System.err.println("Error: ingrese una fecha y hora v�lidas en el formato (yyyy-MM-dd HH:mm:ss)\n");
                }
            } else
            {
                System.err.println("Error: ingrese una fecha y hora v�lidas en el formato (yyyy-MM-dd HH:mm:ss)\n");
            }
        }
    }

    public LocalDate validateLocalDate(Scanner input, String message)
    {
        LocalDate date;
        while (true)
        {
            System.out.print(message);
            String dateString = input.nextLine();

            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(dateString);

            if (matcher.matches())
            {
                try
                {
                    date = LocalDate.parse(dateString);
                    return date;
                } catch (DateTimeParseException e)
                {
                    System.err.println("Error: Ingrese una fecha v�lida (a�o, mes, d�a)\n");
                }
            } else
            {
                System.err.println("Error: Ingrese una fecha v�lida (a�o, mes, d�a) en formato (yyyy-MM-dd)\n");
            }
        }
    }

    public boolean validateDateTimeRange(LocalDateTime startDate, LocalDateTime endDate)
    {
        return endDate.isAfter(startDate);
    }
}
