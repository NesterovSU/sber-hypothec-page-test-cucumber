# language: ru
@myTag
Функция: Ипотека

  Предыстория: Открытие страницы с ипотечным калькулятором
    * Закрыть сообщение куки
    * Выбрать меню 'Ипотека'
    * Выбрать подменю 'Ипотека на готовое жильё'
  @myTag
  Сценарий: Проверка ипотечного калькулятора
    Если Ввести 'Стоимость недвижимости' 5180000 рублей
    И Ввести 'Первоначальный взнос' 3058000 рублей
    И Ввести 'Срок кредита' 30 лет
    И Выключить 'Страхование жизни и здоровья'

    Тогда проверить что 'Ежемесячный платеж' 35459 рублей
    И проверить что 'Сумма кредита' 2122000 рублей
    И проверить что 'Необходимый доход' 49643 рублей
    И проверить что 'Процентная ставка' 11 %


